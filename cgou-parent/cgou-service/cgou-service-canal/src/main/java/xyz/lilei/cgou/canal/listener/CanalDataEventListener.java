package xyz.lilei.cgou.canal.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import xye.lilei.cgou.mq.contants.ExchangeConstants;
import xye.lilei.cgou.mq.contants.TopicConstant;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.content.feign.ContentFeign;
import xyz.lilei.cgou.content.pojo.Content;
import xyz.lilei.cgou.item.feign.PageFeign;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyEventListener
 * @Description TODO
 * @Author lilei
 * @Date 04/07/2020 22:21
 * @Version 1.0
 **/
@Slf4j
@CanalEventListener
public class CanalDataEventListener {

    @Autowired
    private ContentFeign contentFeign;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RabbitTemplate cgRabbitTemplate;

    @Autowired
    private PageFeign pageFeign;

    @ListenPoint(destination = "example",
            schema = "changgou_goods",
            table = {"tb_spu"},
            eventType = {CanalEntry.EventType.UPDATE, CanalEntry.EventType.INSERT, CanalEntry.EventType.DELETE})
    public void onEventCustomSpu(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {

        //判断操作类型
        if (eventType == CanalEntry.EventType.DELETE) {
            String spuId = "";
            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
            for (CanalEntry.Column column : beforeColumnsList) {
                if (column.getName().equals("id")) {
                    spuId = column.getValue();//spuid
                    break;
                }
            }
            //删除静态页
            pageFeign.deleteHtml(Long.valueOf(spuId));
        }else{
            //新增 或者 更新
            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
            String spuId = "";
            for (CanalEntry.Column column : afterColumnsList) {
                if (column.getName().equals("id")) {
                    spuId = column.getValue();
                    break;
                }
            }
            //更新 生成静态页
            pageFeign.createHtml(Long.valueOf(spuId));
        }
    }
    /***
     * 广告数据修改监听
     * @param eventType
     * @param rowData
     */
    @ListenPoint(destination = "example", schema = "changgou_content", table = {"tb_content_category", "tb_content"},
            eventType = {
                CanalEntry.EventType.UPDATE,
                CanalEntry.EventType.DELETE,
                CanalEntry.EventType.INSERT
            })
    public void onEventContentUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        log.info("onEventContentUpdate");
        //1.获取到被修改的category_id
        String categoryId = getColumnValue(eventType, rowData, "category_id");
        //2.调用feign 获取数据
        Result<List<Content>> byCategory = contentFeign.findByCategoryId(Long.valueOf(categoryId));
        //3.存储到redis中
        List<Content> data = byCategory.getData();
        BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps("content_" + categoryId);
        ops.set(JSON.toJSONString(data), 6, TimeUnit.HOURS);
        log.info("同步完成!:{}", categoryId);
    }

    private String getColumnValue(CanalEntry.EventType eventType, CanalEntry.RowData rowData, String columnName) {
        //1.判断更改类型 如果是删除 则需要获取到before的数据
        String result = "";
        if (CanalEntry.EventType.DELETE == eventType) {
            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
            for (CanalEntry.Column column : beforeColumnsList) {
                //column.getName(列的名称   column.getValue() 列对应的值
                if (column.getName().equals(columnName)) {
                    result = column.getValue();
                    return result;
                }
            }
        } else {
            //2判断是 更新 新增 获取after的数据
            List<CanalEntry.Column> beforeColumnsList = rowData.getAfterColumnsList();
            for (CanalEntry.Column column : beforeColumnsList) {
                //column.getName(列的名称   column.getValue() 列对应的值
                if (column.getName().equals(columnName)) {
                    result = column.getValue();
                    return result;
                }
            }
        }
        //3.返回
        return result;
    }

    /***
     * 轮播图数据修改监听
     * @param eventType
     * @param rowData
     */
    @ListenPoint(destination = "example", schema = "changgou_business", table = {"tb_ad"},
            eventType = {
                    CanalEntry.EventType.UPDATE,
                    CanalEntry.EventType.DELETE,
                    CanalEntry.EventType.INSERT
            })
    public void onEventAdUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        String position = getColumnValue(eventType, rowData, "position");
        log.info("onEventAdUpdate :{}", position);
        cgRabbitTemplate.convertAndSend(ExchangeConstants.CANAL_EXCHANGE, TopicConstant.BUSINESS_CACHE_UPDATE_TOPIC, position );
    }

}

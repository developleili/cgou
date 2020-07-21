package xyz.lilei.cgou.item.process;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.lilei.cgou.common.utils.HttpClient;
import xyz.lilei.cgou.item.service.PageService;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @ClassName CanalAdLister
 * @Author lilei
 * @Date 07/07/2020 21:23
 * @Version 1.0
 **/
@Slf4j
@Component
@RabbitListener(queues = "item.tmp.topic")
public class ItemProcessLister {

    @Resource
    private HttpClient httpClient;

    @Resource
    private PageService pageService;

    @RabbitHandler
    public void process(String msg) {
        log.info("监听到ad缓存更新操作:{}", msg);
        String[] split = msg.split(",");

        if ("1".equals(split[0])){
                pageService.createPageHtml(Long.valueOf(split[1]));
        }else {
                pageService.deleteHtml(Long.valueOf(split[1]));
        }
    }

}

package xyz.lilei.cgou.business.mq.process;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xye.lilei.cgou.mq.contants.TopicConstant;
import xyz.lilei.cgou.common.utils.HttpClient;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @ClassName CanalAdLister
 * @Author lilei
 * @Date 07/07/2020 21:23
 * @Version 1.0
 **/
@Slf4j
@Component
@RabbitListener(queues = "business.cache.topic")
public class CanalAdLister {

    @Value("${business.ad-update-url}")
    private String adUpdateUrl;


    @RabbitHandler
    public void process(String msg) {
        log.info("监听到ad缓存更新操作:{}", msg);
        try {
            Map data = new HashMap();
            data.put("position", msg);
            HttpClient client = new HttpClient(adUpdateUrl, data);
            client.get();
        } catch (Exception e) {
            log.info("ad操作失败:{}", e.getMessage());
        }
        // 调用nginx中lua刷新脚本
        System.out.println("TopicEmailMessageReceiver  : " +msg);
    }

}

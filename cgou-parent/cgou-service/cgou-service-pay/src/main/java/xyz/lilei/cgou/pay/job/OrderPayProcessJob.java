package xyz.lilei.cgou.pay.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName PayStatusJob
 * @Author lilei
 * @Date 13/07/2020 22:35
 * @Version 1.0
 **/
@Component
public class OrderPayProcessJob {

    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0/5 * * * * ? ")
    private void scheduler(){
        Map orderList = redisTemplate.boundHashOps("order").entries();
    }
}

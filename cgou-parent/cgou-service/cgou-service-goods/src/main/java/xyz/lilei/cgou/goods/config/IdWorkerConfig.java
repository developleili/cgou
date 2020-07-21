package xyz.lilei.cgou.goods.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.lilei.cgou.common.utils.IdWorker;

/**
 * @ClassName IdWokerConfig
 * @Description TODO
 * @Author lilei
 * @Date 03/07/2020 20:27
 * @Version 1.0
 **/
@Configuration
public class IdWorkerConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,0);
    }
}

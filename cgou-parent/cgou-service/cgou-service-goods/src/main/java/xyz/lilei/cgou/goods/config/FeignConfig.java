package xyz.lilei.cgou.goods.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.lilei.cgou.common.interceptor.FeignInterceptor;

/**
 * @ClassName FeignConfig
 * @Author lilei
 * @Date 12/07/2020 22:40
 * @Version 1.0
 **/
@Configuration
public class FeignConfig {

    @Bean
    public FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }
}

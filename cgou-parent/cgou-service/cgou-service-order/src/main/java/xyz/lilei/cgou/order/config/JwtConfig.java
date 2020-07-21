package xyz.lilei.cgou.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import xyz.lilei.cgou.common.component.TokenDecode;
import xyz.lilei.cgou.common.interceptor.FeignInterceptor;

/**
 * @ClassName JwtConfig
 * @Author lilei
 * @Date 12/07/2020 17:43
 * @Version 1.0
 **/
@Configuration
public class JwtConfig {


    /***
     * 创建拦截器Bean对象
     * @return
     */
    @Bean
    public FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }

    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
}

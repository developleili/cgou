package xyz.lilei.cgou.gateway.web;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @ClassName GatewayWebApplication
 * @Author lilei
 * @Date 08/07/2020 00:17
 * @Version 1.0
 **/

@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class GatewayWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GatewayWebApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        log.info("配置信息 :{}" ,JSONObject.toJSONString(context.getBeansOfType(KeyResolver.class)));
        if (log.isDebugEnabled()) {
            log.debug("配置信息=>:{}", environment);
            log.info("配置信息 :{}" ,JSONObject.toJSONString(context.getBeansOfType(KeyResolver.class)));
            log.debug("eureka.client.serviceUrl.defaultZone：{}", environment.getProperty("eureka.client.serviceUrl.defaultZone"));
        }
    }

    @Bean(name = "ipKeyResolver")
    public KeyResolver ipKeyResolver(){
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
    }
    //创建一个ipKeyResolver 指定用户的IP


}

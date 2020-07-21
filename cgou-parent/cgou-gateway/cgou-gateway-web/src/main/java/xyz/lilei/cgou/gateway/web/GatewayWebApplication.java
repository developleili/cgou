package xyz.lilei.cgou.gateway.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import reactor.core.publisher.Mono;

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
        if (log.isDebugEnabled()) {
            log.debug("配置信息=>:{}", environment);
            log.debug("eureka.client.serviceUrl.defaultZone：{}", environment.getProperty("eureka.client.serviceUrl.defaultZone"));
        }
    }

    @Bean("ipKeyResolver")
    public KeyResolver ipKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}

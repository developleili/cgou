package xyz.lilei.cgou.canal;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import xye.lilei.cgou.mq.annotation.EnableRabbitMQ;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableCanalClient
@EnableFeignClients(basePackages = {"xyz.lilei.cgou"})
@EnableRabbit
@Slf4j
public class CanalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CanalApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        if (log.isDebugEnabled()) {
            log.debug("配置信息=>:{}", environment);
            log.debug("eureka.client.serviceUrl.defaultZone：{}", environment.getProperty("eureka.client.serviceUrl.defaultZone"));
        }

    }


}

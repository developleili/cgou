package xyz.lilei.cgou.sleuth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import zipkin2.server.internal.EnableZipkinServer;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class CgouSleuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CgouSleuthApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
            log.info("配置信息=>:{}", env);
            log.info("eureka.client.serviceUrl.defaultZone：{}", env.getProperty("eureka.client.serviceUrl.defaultZone"));
    }

}

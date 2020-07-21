package xyz.lilei.cgou.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

@Slf4j
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
        if (log.isDebugEnabled()) {
            log.debug("配置信息=>:{}", env);
            log.debug("eureka.client.serviceUrl.defaultZone：{}", env.getProperty("eureka.client.serviceUrl.defaultZone"));
        }
    }
}

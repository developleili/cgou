package xyz.lilei.cgou.goods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"xyz.lilei.cgou.goods.dao"})
@Slf4j
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
        if (log.isDebugEnabled()) {
            log.debug("配置信息=>:{}", env);
            log.debug("eureka.client.serviceUrl.defaultZone：{}", env.getProperty("eureka.client.serviceUrl.defaultZone"));
        }
    }

}

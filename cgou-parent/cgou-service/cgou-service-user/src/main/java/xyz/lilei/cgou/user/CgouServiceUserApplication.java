package xyz.lilei.cgou.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"xyz.lilei.cgou.user.dao"})
public class CgouServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CgouServiceUserApplication.class, args);
    }

}

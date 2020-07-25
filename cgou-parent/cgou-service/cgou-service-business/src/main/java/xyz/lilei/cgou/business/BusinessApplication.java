package xyz.lilei.cgou.business;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName BusinessApplication
 * @Author lilei
 * @Date 07/07/2020 22:10
 * @Version 1.0
 **/
@SpringBootApplication
@EnableRabbit
//开启断路器功能
@EnableCircuitBreaker
@MapperScan(basePackages = {"xyz.lilei.cgou.business.dao"})
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }


}

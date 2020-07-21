package xyz.lilei.cgou.business;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
import xye.lilei.cgou.mq.annotation.EnableRabbitMQ;

/**
 * @ClassName BusinessApplication
 * @Author lilei
 * @Date 07/07/2020 22:10
 * @Version 1.0
 **/
@SpringBootApplication
@EnableRabbit
@MapperScan(basePackages = {"xyz.lilei.cgou.business.dao"})
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }
}

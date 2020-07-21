package xyz.lilei.cgou.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;
import xyz.lilei.cgou.common.interceptor.FeignInterceptor;
import xyz.lilei.cgou.common.utils.IdWorker;

@MapperScan(basePackages = "xyz.lilei.cgou.order.dao")
@EnableFeignClients(basePackages = {"xyz.lilei.cgou.order.feign", "xyz.lilei.cgou.goods.feign","xyz.lilei.cgou.user.feign"})
@EnableEurekaClient
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /***
     * 创建拦截器Bean对象
     * @return
     */
    @Bean
    public FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}

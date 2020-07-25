package xyz.lilei.cgou.business.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EurekaHealthIndicator;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import xyz.lilei.cgou.business.controller.AdController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName BussinessHealthIndicator
 * @Author lilei
 * @Date 22/07/2020 23:36
 * @Version 1.0
 **/
@Configuration
public class BusinessHealthIndicator implements HealthIndicator {

    @Resource
    private DataSource dataSource;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public Health health() {
        try {
            if (dataSource.getConnection().isValid(2)){
                return new Health.Builder(Status.UP).build();
            }else {
                return new Health.Builder(Status.DOWN).build();
            }
        } catch (SQLException e) {
            return new Health.Builder(Status.DOWN).build();
        }
    }
}

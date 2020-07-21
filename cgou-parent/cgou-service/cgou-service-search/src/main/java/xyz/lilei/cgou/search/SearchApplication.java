package xyz.lilei.cgou.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"xyz.lilei.cgou.goods.feign"})
@EnableElasticsearchRepositories(basePackages = "xyz.lilei.cgou.search.dao")
@Slf4j
public class SearchApplication {

    public static void main(String[] args) {
        /**
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         * java.lang.IllegalStateException: availableProcessors is already set to [12], rejecting [12]
         ***/
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        ConfigurableApplicationContext run = SpringApplication.run(SearchApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        if (log.isDebugEnabled()) {
            log.debug("配置信息=>:{}", environment);
            log.debug("eureka.client.serviceUrl.defaultZone：{}", environment.getProperty("eureka.client.serviceUrl.defaultZone"));
        }

    }


}

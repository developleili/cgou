package xyz.lilei.cgou.business.config;

import com.netflix.loadbalancer.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Supplier;

/**
 * @ClassName RestConfig
 * @Author lilei
 * @Date 23/07/2020 12:11
 * @Version 1.0
 **/
@Configuration
public class RestConfig {


    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(){
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(5000);
        simpleClientHttpRequestFactory.setReadTimeout(5000);
        return simpleClientHttpRequestFactory;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory){
        return new RestTemplateBuilder().requestFactory(() -> clientHttpRequestFactory).errorHandler((new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        })).build();
    }

    @Bean
    public IRule ribbonRule(){
        //线性轮询
        new RoundRobinRule();
        //可重试轮询
        new RetryRule();
        //权重
        new WeightedResponseTimeRule();
        //选择压力最小的示例
        new BestAvailableRule();
        return new RandomRule();
    }
}

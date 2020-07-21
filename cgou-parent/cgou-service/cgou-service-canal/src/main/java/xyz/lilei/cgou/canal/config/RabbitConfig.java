package xyz.lilei.cgou.canal.config;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xye.lilei.cgou.mq.contants.ExchangeConstants;
import xye.lilei.cgou.mq.contants.TopicConstant;

/**
 * @ClassName RabbitConfig
 * @Author lilei
 * @Date 07/07/2020 21:57
 * @Version 1.0
 **/
@Slf4j
@Configuration
public class RabbitConfig implements InitializingBean {

//    @Autowired
//    private AmqpAdmin amqpAdmin;
//    @Bean
//    Queue businessQueue() {
//        return new Queue(TopicConstant.BUSINESS_CACHE_UPDATE_TOPIC, false);
//    }
//
//    @Bean
//    Queue itemQueue() {
//        return new Queue(TopicConstant.ITEM_TEMPLATE_PROCESS_TOPIC, false);
//    }
//
//    @Bean
//    TopicExchange canalExchange() {
//        return new TopicExchange(ExchangeConstants.CANAL_EXCHANGE);
//    }
//
//    @Bean
//    Binding businessBinding(Queue businessQueue, TopicExchange canalExchange) {
//        return BindingBuilder.bind(businessQueue).to(canalExchange).with(TopicConstant.BUSINESS_CACHE_UPDATE_TOPIC);
//    }
//
//    @Bean
//    Binding itemBinding(Queue itemQueue, TopicExchange canalExchange) {
//        return BindingBuilder.bind(itemQueue).to(canalExchange).with(TopicConstant.ITEM_TEMPLATE_PROCESS_TOPIC);
//    }
//

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("config初始化");
    }

}

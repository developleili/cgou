package xye.lilei.cgou.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import xye.lilei.cgou.mq.contants.ExchangeConstants;
import xye.lilei.cgou.mq.contants.TopicConstant;

import java.util.concurrent.ForkJoinPool;

/**
 * @ClassName AmqpConfig
 * @Desc 初始化rabbit连接池
 * @Author lilei
 * @Date 06/07/2020 23:46
 * @Version 1.0
 **/
@Slf4j
public class AmqpConfig implements ApplicationContextAware {

    @Autowired
    private AmqpProperties amqpProperties;

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(amqpProperties.getHost());
        cachingConnectionFactory.setPort(amqpProperties.getPort());
        cachingConnectionFactory.setUsername(amqpProperties.getUserName());
        cachingConnectionFactory.setPassword(amqpProperties.getPassword());
        cachingConnectionFactory.setSimplePublisherConfirms(amqpProperties.isPublisherConfirms());
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate cgRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 失败通知
        rabbitTemplate.setMandatory(true);
        // 发送方确认
        rabbitTemplate.setConfirmCallback((correlationData, b, s) -> {
            if (b) {
                log.info("发送者确认发送给mq成功");
            } else {
                //处理失败的消息
                log.info("发送者发送给mq失败,考虑重发:{}",s);
            }
        });
        // 失败回调
        rabbitTemplate.setReturnCallback((message, i, s, s1, s2) -> {
            log.info("无法路由的消息，需要考虑另外处理。");
            log.info("Returned replyText:{}",s);
            log.info("Returned exchange:{}"+s1);
            log.info("Returned routingKey:{}", s2);
            String msgJson  = new String(message.getBody());
            log.info("Returned Message：{}", msgJson);
        });
        log.info("初始化成功");
        return rabbitTemplate;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        Map<String, Object> map = applicationContext.getBeansWithAnnotation(AmqpMsgListener.class);
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//
//        }
    }



}

package xye.lilei.cgou.mq.annotation;

import org.springframework.context.annotation.Import;
import xye.lilei.cgou.mq.AmqpConfig;
import xye.lilei.cgou.mq.AmqpProperties;

import java.lang.annotation.*;

/**
 * @ClassName EnableAmqpMq
 * @Author lilei
 * @Date 07/07/2020 12:48
 * @Version 1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({AmqpProperties.class, AmqpConfig.class})
public @interface EnableRabbitMQ {
}

package xye.lilei.cgou.mq.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName AmqpMsgListener
 * @Author lilei
 * @Date 07/07/2020 20:45
 * @Version 1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface AmqpMsgListener {
}

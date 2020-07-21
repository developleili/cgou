package xye.lilei.cgou.mq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @ClassName AmqpProperties
 * @Author lilei
 * @Date 07/07/2020 12:46
 * @Version 1.0
 **/
@Data
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "cgou.amqp")
public class AmqpProperties {

    /**
     * canal server host
     */
    private String host = "127.0.0.1";

    /**
     * canal server port
     */
    private int port = 5672;

    private String virtualHost;


    /**
     * canal user name
     */
    private String userName = "";

    /**
     * canal password
     */
    private String password = "";

    /**
     * size when get messages from the canal server
     */
    private int batchSize = 1000;

    /**
     * filter
     */
    private String filter;

    /**
     * retry count when error occurred
     */
    private int retryCount = 5;

    /**
     * interval of the message-acquiring
     */
    private long acquireInterval = 1000;

    private boolean publisherConfirms = false;
}

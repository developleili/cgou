package xye.lilei.cgou.mq;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MessageBody
 * @Author lilei
 * @Date 07/07/2020 08:57
 * @Version 1.0
 **/
@Data
public class MessageBody implements Serializable {
    private String from_system;
    private String publish_time;
    private List<Object> publish_data;
}

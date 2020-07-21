package xye.lilei.cgou.mq;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public abstract class AbsAmqpMsgListener implements MessageListener {

  public abstract boolean onMessage(String msg);

  public String topic() {
    return null;
  };

  @Override
  public void onMessage(Message message) {

  }


}

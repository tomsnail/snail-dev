package cn.tomsnail.mq.aliyun.tcp;


import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

public abstract class CustomMessageListener implements MessageListener{

	public Action consume(Message message, ConsumeContext context) {
        System.out.println("Receive: " + message.getMsgID());
        try {
			handler(message.getTopic(),message.getTag(),message.getMsgID(),message.getKey(),new String(message.getBody(),"UTF-8"),message.getStartDeliverTime());
            return Action.CommitMessage;
        }catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }
	
	public abstract void handler(String topic,String tag,String msgId,String key,String body,long time);
	
}

package cn.tomsnail.mq.aliyun.tcp;

import java.text.SimpleDateFormat;

import cn.tomsnail.mq.aliyun.ProducerConfig;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.order.OrderProducer;

public class ProducerSendMsg {

	public static boolean sendOrderMsg(OrderProducer orderProducer,String topic,String tag,String body,String orderId){
		if(orderProducer==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Message msg = new Message(topic,tag,body.getBytes());
		if(orderId!=null){
			msg.setKey(orderId);
		}
		try {
			String shardingKey = String.valueOf(orderId);
			orderProducer.send(msg, shardingKey);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean sendTransactionMsg(ProducerConfig producerConfig,String topic,String tag,String body){
		return false;
	}
	
	public static boolean sendSyncMsg(Producer producer,String topic,String tag,String body,String orderId){
		if(producer==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Message msg = new Message(topic,tag,body.getBytes());
		if(orderId!=null){
			msg.setKey(orderId);
		}
		try {
			producer.send(msg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean sendAsyncMsg(Producer producer,String topic,String tag,String body,String orderId){
		if(producer==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Message msg = new Message(topic,tag,body.getBytes());
		if(orderId!=null){
			msg.setKey(orderId);
		}
		try {
			producer.sendAsync(msg, new SendCallback() {
	            @Override
	            public void onSuccess(final SendResult sendResult) {
	                System.out.println("send message success. topic=" + sendResult.getTopic() + ", msgId=" + sendResult.getMessageId());
	            }
	            @Override
	            public void onException(OnExceptionContext context) {
	                System.out.println("send message failed. topic=" + context.getTopic() + ", msgId=" + context.getMessageId());
	            }
	        });
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean sendOnewayMsg(Producer producer,String topic,String tag,String body,String orderId){
		if(producer==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Message msg = new Message(topic,tag,body.getBytes());
		if(orderId!=null){
			msg.setKey(orderId);
		}
		try {
			producer.sendOneway(msg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean sendDeliverMsg(Producer producer,String topic,String tag,String body,String orderId,long delayTime ){
		if(producer==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Message msg = new Message(topic,tag,body.getBytes());
		if(orderId!=null){
			msg.setKey(orderId);
		}
		try {
	        msg.setStartDeliverTime(System.currentTimeMillis() + delayTime);
			producer.send(msg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean sendTimeMsg(Producer producer,String topic,String tag,String body,String orderId,String time){
		if(producer==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Message msg = new Message(topic,tag,body.getBytes());
		if(orderId!=null){
			msg.setKey(orderId);
		}
		try {
			long timeStamp =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
	        msg.setStartDeliverTime(timeStamp);
			producer.send(msg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}

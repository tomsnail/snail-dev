package cn.tomsnail.snail.e3.aliyun.mq.tcp;

import java.text.SimpleDateFormat;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;

import cn.tomsnail.snail.e3.aliyun.mq.ProducerConfig;

public class SendMsg {

	public static boolean sendOrderMsg(ProducerConfig producerConfig,String topic,String tag,String body,String orderId){
		if(producerConfig==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		OrderProducer orderProducer = ProducerFactory.getOrderProducer(producerConfig);
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
	
	public static boolean sendSyncMsg(ProducerConfig producerConfig,String topic,String tag,String body,String orderId){
		if(producerConfig==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Producer producer = ProducerFactory.getProducer(producerConfig);
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
	
	public static boolean sendAsyncMsg(ProducerConfig producerConfig,String topic,String tag,String body,String orderId){
		if(producerConfig==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Producer producer = ProducerFactory.getProducer(producerConfig);
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
	
	public static boolean sendOnewayMsg(ProducerConfig producerConfig,String topic,String tag,String body,String orderId){
		if(producerConfig==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Producer producer = ProducerFactory.getProducer(producerConfig);
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
	
	public static boolean sendDeliverMsg(ProducerConfig producerConfig,String topic,String tag,String body,String orderId,long delayTime ){
		if(producerConfig==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Producer producer = ProducerFactory.getProducer(producerConfig);
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
	
	public static boolean sendTimeMsg(ProducerConfig producerConfig,String topic,String tag,String body,String orderId,String time){
		if(producerConfig==null||topic==null||body==null){
			return false;
		}
		if(tag==null){
			tag = "A";
		}
		Producer producer = ProducerFactory.getProducer(producerConfig);
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

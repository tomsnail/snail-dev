package cn.tomsnail.mq.aliyun.tcp;

import java.io.UnsupportedEncodingException;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

import cn.tomsnail.mq.aliyun.ConsumerConfig;

public class SubscribeMsg {

	public void subscribe(ConsumerConfig consumerConfig,String topic,String tag,SubscribeHandler subscribeHandler){
		if(topic==null||subscribeHandler==null||consumerConfig==null){
			return;
		}
		if(tag==null){
			tag = "*";
		}
		 Consumer consumer = ConsumerFactory.getConsumer(consumerConfig);
		 if(consumer!=null){
			 consumer.subscribe(topic, tag, new MessageListener() {
				//订阅全部Tag
		            public Action consume(Message message, ConsumeContext context) {
		                System.out.println("Receive: " + message);
		                try {
							subscribeHandler.handler(new String(message.getBody(),"UTF-8"));
						} catch (UnsupportedEncodingException e) {
						}
		                return Action.CommitMessage;
		            }
		        });
		 }
	}
	
	
	public void subscribe(Consumer consumer,String topic,String tag,SubscribeHandler subscribeHandler){
		if(topic==null||subscribeHandler==null||consumer==null){
			return;
		}
		if(tag==null){
			tag = "*";
		}
		 if(consumer!=null){
			 consumer.subscribe(topic, tag, new MessageListener() {
				//订阅全部Tag
		            public Action consume(Message message, ConsumeContext context) {
		                System.out.println("Receive: " + message);
		                try {
							subscribeHandler.handler(new String(message.getBody(),"UTF-8"));
			                return Action.CommitMessage;
						} catch (Exception e) {
			                return Action.ReconsumeLater;
						}
		            }
		        });
		 }
	}
	
}

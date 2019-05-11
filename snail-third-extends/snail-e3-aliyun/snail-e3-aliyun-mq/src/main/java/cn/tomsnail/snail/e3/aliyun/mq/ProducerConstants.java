package cn.tomsnail.snail.e3.aliyun.mq;

import java.util.UUID;


public class ProducerConstants {

	public static String getOrderId(){
		return System.currentTimeMillis()+UUID.randomUUID().toString();
	}
	
}

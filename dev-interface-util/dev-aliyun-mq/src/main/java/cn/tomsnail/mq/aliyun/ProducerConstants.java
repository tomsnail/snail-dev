package cn.tomsnail.mq.aliyun;

import java.util.UUID;


public class ProducerConstants {

	public static String getOrderId(){
		return System.currentTimeMillis()+UUID.randomUUID().toString();
	}
	
}

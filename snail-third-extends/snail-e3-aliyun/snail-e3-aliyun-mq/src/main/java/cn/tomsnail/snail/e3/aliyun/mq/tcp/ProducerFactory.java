package cn.tomsnail.snail.e3.aliyun.mq.tcp;

import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;

import cn.tomsnail.snail.e3.aliyun.mq.ProducerConfig;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年5月16日 下午3:06:54
	* @see 
	*/     
public class ProducerFactory {

	public static OrderProducer getOrderProducer(ProducerConfig producerConfig){
		return null;
	}
	
	public static TransactionProducer  getTransactionProducer(ProducerConfig producerConfig){
		return null;
	}
	
	public static Producer  getProducer(ProducerConfig producerConfig){
		return null;
	}
	
}

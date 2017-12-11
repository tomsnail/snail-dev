package cn.tomsnail.mq.aliyun.tcp;

import cn.tomsnail.mq.aliyun.ProducerConfig;

import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;

  
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

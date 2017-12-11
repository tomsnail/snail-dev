package cn.tomsnail.jms.kafka.consumer;


import cn.tomsnail.jms.IConsumerClient;
import cn.tomsnail.jms.IJmsConsumerFactory;
import cn.tomsnail.jms.MQConfig;

/**
 *        kafka消费工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月8日 上午10:14:23
 * @see 
 */
public class KafkaConsumerFactory implements IJmsConsumerFactory{
	
	@Override
	public IConsumerClient getConsumerClient(MQConfig mqConfig) {
		try {
			return new KafkaConsumerClient(mqConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

package cn.tomsnail.snail.ext.mq.core.kafka.consumer;

import cn.tomsnail.snail.ext.mq.core.IConsumerClient;
import cn.tomsnail.snail.ext.mq.core.IJmsConsumerFactory;
import cn.tomsnail.snail.ext.mq.core.MQConfig;

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

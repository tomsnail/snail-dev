package cn.tomsnail.snail.ext.mq.core.mq.activemq;

import cn.tomsnail.snail.ext.mq.core.IConsumerClient;
import cn.tomsnail.snail.ext.mq.core.IJmsConsumerFactory;
import cn.tomsnail.snail.ext.mq.core.MQConfig;

/**
 *        ActiveMQConsumerFactory
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月18日 下午4:04:00
 * @see 
 */
public class ActiveMQConsumerFactory implements IJmsConsumerFactory{

	@Override
	public IConsumerClient getConsumerClient(MQConfig mqConfig) {		
		return new ActiveMQConsumerClient();
	}

}

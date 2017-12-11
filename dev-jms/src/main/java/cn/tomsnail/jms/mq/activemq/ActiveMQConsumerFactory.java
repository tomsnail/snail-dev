package cn.tomsnail.jms.mq.activemq;

import cn.tomsnail.jms.IConsumerClient;
import cn.tomsnail.jms.IJmsConsumerFactory;
import cn.tomsnail.jms.MQConfig;

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

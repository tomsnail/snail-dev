package cn.tomsnail.jms.mq.activemq;

import cn.tomsnail.jms.IJmsSender;
import cn.tomsnail.jms.IJmsSenderFactory;
import cn.tomsnail.jms.MQConfig;

/**
 *        ActiveMQSenderFactory
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月18日 下午4:04:23
 * @see 
 */
public class ActiveMQSenderFactory implements IJmsSenderFactory{

	@Override
	public IJmsSender getJmsSender(MQConfig mqConfig) {
		return null;
	}

}

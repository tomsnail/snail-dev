package cn.tomsnail.jms.mq.activemq;

import cn.tomsnail.jms.IConsumerClient;
import cn.tomsnail.jms.IJmsReceiveCall;
import cn.tomsnail.jms.MQConfig;

/**
 *        ActiveMQConsumerClient
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月18日 下午4:34:27
 * @see 
 */
public class ActiveMQConsumerClient implements IConsumerClient{
	
	private MQConfig mqConfig;

	@Override
	public void register(IJmsReceiveCall jmsReceiveCall) {
		register(mqConfig,jmsReceiveCall);
	}

	@Override
	public void register(MQConfig mqConfig, IJmsReceiveCall jmsReceiveCall) {
		JmsReceiverConfig jmsReceiverConfig = new JmsReceiverConfig();
		jmsReceiverConfig.setUrl(mqConfig.getUrl());
		jmsReceiverConfig.setSubject(mqConfig.getName());
		jmsReceiverConfig.setConsumerName(mqConfig.getName()+Thread.currentThread().getName());
		jmsReceiverConfig.setCoreNumber(1);
		jmsReceiverConfig.setTopic(false);
		JmsReceiver jmsReceiver = new JmsReceiver();
		jmsReceiver.setJmsReceiverConfig(jmsReceiverConfig);
		jmsReceiver.setJmsReceiveHandler(new TestJmsHandler());
		jmsReceiver.start();
	}

	@Override
	public void close() {
		
	}

	@Override
	public void init() {
		
	}

	public MQConfig getMqConfig() {
		return mqConfig;
	}

	public void setMqConfig(MQConfig mqConfig) {
		this.mqConfig = mqConfig;
	}
	
	

}

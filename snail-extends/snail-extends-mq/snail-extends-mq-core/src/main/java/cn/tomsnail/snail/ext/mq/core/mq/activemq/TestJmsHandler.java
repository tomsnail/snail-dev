package cn.tomsnail.snail.ext.mq.core.mq.activemq;

import cn.tomsnail.snail.ext.mq.core.Message;

public class TestJmsHandler implements JmsReceiveHandler{

	
	
	public static void main(String[] args) {
		JmsReceiverConfig jmsReceiverConfig = new JmsReceiverConfig();
		jmsReceiverConfig.setUrl("tcp://192.168.1.197:61616");
		jmsReceiverConfig.setSubject("testq");
		jmsReceiverConfig.setConsumerName("testq2");
		jmsReceiverConfig.setCoreNumber(1);
		jmsReceiverConfig.setTopic(true);
		JmsReceiver jmsReceiver = new JmsReceiver();
		jmsReceiver.setJmsReceiverConfig(jmsReceiverConfig);
		jmsReceiver.setJmsReceiveHandler(new TestJmsHandler());
		jmsReceiver.start();
	}

	@Override
	public void call(Message msg) {
		
	}

	

}

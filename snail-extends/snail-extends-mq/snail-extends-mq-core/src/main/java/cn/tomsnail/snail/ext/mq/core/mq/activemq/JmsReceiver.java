package cn.tomsnail.snail.ext.mq.core.mq.activemq;

import java.util.ArrayList;
import java.util.List;

/**
 *        jms接收
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:19:17
 * @see 
 */
public class JmsReceiver {
	
	private List<ActiveMQConsumer> activeMQConsumers = new ArrayList<ActiveMQConsumer>();

	private JmsReceiverConfig jmsReceiverConfig;
	
	private JmsReceiveHandler jmsReceiveHandler;
		
	public JmsReceiver(){
		
	}
	
	public void start(){
		for(int i=0;i<jmsReceiverConfig.getCoreNumber();i++){
			ActiveMQConsumer activeMQConsumer = new ActiveMQConsumer(jmsReceiverConfig.getSubject(),jmsReceiverConfig.getUrl(),jmsReceiverConfig.getConsumerName()+""+i,jmsReceiverConfig.isTopic());
			activeMQConsumer.setJmsReceiveHandler(jmsReceiveHandler);
			activeMQConsumers.add(activeMQConsumer);
			activeMQConsumer.start();
			
		}
	}
	
	public void stop(){
		for(ActiveMQConsumer activeMQConsumer:activeMQConsumers){
			activeMQConsumer.exit();
		}
	}

	public JmsReceiverConfig getJmsReceiverConfig() {
		return jmsReceiverConfig;
	}
	public void setJmsReceiverConfig(JmsReceiverConfig jmsReceiverConfig) {
		this.jmsReceiverConfig = jmsReceiverConfig;
	}
	public JmsReceiveHandler getJmsReceiveHandler() {
		return jmsReceiveHandler;
	}
	
	public void setJmsReceiveHandler(JmsReceiveHandler jmsReceiveHandler) {
		this.jmsReceiveHandler = jmsReceiveHandler;
	}
	
	
	
}

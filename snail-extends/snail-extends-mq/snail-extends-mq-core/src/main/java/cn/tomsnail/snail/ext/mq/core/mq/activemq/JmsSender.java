package cn.tomsnail.snail.ext.mq.core.mq.activemq;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *        消息发送者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年1月19日 下午1:23:20
 * @see 
 */
public class JmsSender {
	
	private LinkedBlockingQueue<String> QUEUE = null;
	
	private JmsSenderConfig jmsSenderConfig;
	
	public JmsSender(JmsSenderConfig jmsSenderConfig){
		this.jmsSenderConfig = jmsSenderConfig;
		QUEUE = new LinkedBlockingQueue<String>(jmsSenderConfig.getLocalCacheNumber());
		init();
	}
	
	private void init(){
		int threadNumber = this.jmsSenderConfig.getCoreSenderNumber();
		if(threadNumber<1){
			threadNumber = 1;
		}
		for(int i=0;i<threadNumber;i++){
			ActiveMQProducer activeMQProducer = new ActiveMQProducer(jmsSenderConfig.getUrl(), jmsSenderConfig.getSubject(), QUEUE,jmsSenderConfig.isTopic());
			activeMQProducer.start();
		}
	}
	public void sendMsg(String msg){
		try {
			QUEUE.put(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public JmsSenderConfig getJmsSenderConfig() {
		return jmsSenderConfig;
	}
	public void setJmsSenderConfig(JmsSenderConfig jmsSenderConfig) {
		this.jmsSenderConfig = jmsSenderConfig;
	}
	
	public static void main(String[] args) {
		JmsSenderConfig jmsSenderConfig = new JmsSenderConfig();
		jmsSenderConfig.setUrl("tcp://192.168.1.197:61616");
		jmsSenderConfig.setSubject("testq");
		jmsSenderConfig.setTopic(true);
		JmsSender jmsSender = new JmsSender(jmsSenderConfig);
		for(int i=0;i<2;i++){
			jmsSender.sendMsg("test number is :"+i);
		}
	}
}

package cn.tomsnail.jms.kafka.consumer;


import cn.tomsnail.jms.IConsumerClient;
import cn.tomsnail.jms.IJmsReceiveCall;
import cn.tomsnail.jms.MQConfig;
import cn.tomsnail.jms.kafka.KafkaMQConfig;

/**
 *        kafka消费者客户端
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月8日 上午10:18:04
 * @see 
 */
public class KafkaConsumerClient implements IConsumerClient{
	
	
	/**
	 * 
	 */
	private MQConfig mqConfig;
	
	public KafkaConsumerClient(){
		
	}
	
	public KafkaConsumerClient(MQConfig mqConfig){
		this.mqConfig = mqConfig;
	}
	
	@Override
	public void register(IJmsReceiveCall jmsReceiveCall) {
		register(mqConfig,jmsReceiveCall);
	}

	@Override
	public void register(MQConfig mqConfig, IJmsReceiveCall jmsReceiveCall) {
		if(mqConfig!=null){
			KafkaConsumer consumer =  new KafkaConsumer((KafkaMQConfig) mqConfig);
			consumer.setJmsReceiveCall(jmsReceiveCall);
			new Thread(consumer).start();
		}
		
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

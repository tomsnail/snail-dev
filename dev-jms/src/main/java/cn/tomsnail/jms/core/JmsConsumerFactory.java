package cn.tomsnail.jms.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.tomsnail.jms.IConsumerClient;
import cn.tomsnail.jms.IJmsConsumerFactory;
import cn.tomsnail.jms.JmsType;
import cn.tomsnail.jms.MQConfig;
import cn.tomsnail.jms.kafka.consumer.KafkaConsumerFactory;
import cn.tomsnail.jms.mq.activemq.ActiveMQConsumerFactory;

/**
 *          jms消费者工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月18日 上午11:52:36
 * @see 
 */
public class JmsConsumerFactory implements IJmsConsumerFactory{

	private static  Map<JmsType,IJmsConsumerFactory> FACTORY_MAP = new HashMap<JmsType, IJmsConsumerFactory>();

	private static final Lock LOCK = new ReentrantLock();

	@Override
	public IConsumerClient getConsumerClient(MQConfig mqConfig) {
		if(mqConfig==null||mqConfig.getJmsType()==null||mqConfig.getName()==null){
			return null;
		}
		try {
			LOCK.lock();
			if(!FACTORY_MAP.containsKey(mqConfig.getJmsType())){
				if(mqConfig.getJmsType().compareTo(JmsType.REDIS)==0){
					FACTORY_MAP.put(JmsType.REDIS,(IJmsConsumerFactory)Class.forName(mqConfig.getFactoryClass()).newInstance());
				}
				if(mqConfig.getJmsType().compareTo(JmsType.ACTIVEMQ)==0){
					ActiveMQConsumerFactory activeMQConsumerFactory = new ActiveMQConsumerFactory();
					FACTORY_MAP.put(JmsType.ACTIVEMQ,activeMQConsumerFactory);
				}
				if(mqConfig.getJmsType().compareTo(JmsType.KAFKA)==0){
					KafkaConsumerFactory kafkaConsumerFactory = new KafkaConsumerFactory();
					FACTORY_MAP.put(JmsType.KAFKA,kafkaConsumerFactory);
				}
			}
		} catch (Exception e) {
			
		}finally{
			LOCK.unlock();
		}
		return FACTORY_MAP.get(mqConfig.getJmsType()).getConsumerClient(mqConfig);
	}
	
	public void setFactoryMap(Map<JmsType,IJmsConsumerFactory> factroyMap ){
		FACTORY_MAP = factroyMap;
	}

}

package cn.tomsnail.snail.ext.mq.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.tomsnail.snail.ext.mq.core.IJmsSender;
import cn.tomsnail.snail.ext.mq.core.IJmsSenderFactory;
import cn.tomsnail.snail.ext.mq.core.JmsType;
import cn.tomsnail.snail.ext.mq.core.MQConfig;
import cn.tomsnail.snail.ext.mq.core.kafka.sender.KafkaSenderFactory;
import cn.tomsnail.snail.ext.mq.core.mq.activemq.ActiveMQSenderFactory;

/**
 *        jms发送者工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月18日 上午10:46:38
 * @see 
 */
public class JmsSenderFactory implements IJmsSenderFactory{
	
	private static  Map<JmsType,IJmsSenderFactory> FACTORY_MAP = new HashMap<JmsType, IJmsSenderFactory>();
	
	private static final Lock LOCK = new ReentrantLock();
	
	public  IJmsSender getJmsSender(MQConfig mqConfig){
		if(mqConfig==null||mqConfig.getJmsType()==null||mqConfig.getName()==null){
			return null;
		}
		try {
			LOCK.lock();
			if(!FACTORY_MAP.containsKey(mqConfig.getJmsType())){
//				if(mqConfig.getJmsType().compareTo(JmsType.REDIS)==0){
//					RedisMQSenderFactory redisMQSenderFactory = new RedisMQSenderFactory();
//					FACTORY_MAP.put(JmsType.REDIS,redisMQSenderFactory);
//				}
				if(mqConfig.getJmsType().compareTo(JmsType.ACTIVEMQ)==0){
					ActiveMQSenderFactory activeMQSenderFactory = new ActiveMQSenderFactory();
					FACTORY_MAP.put(JmsType.ACTIVEMQ,activeMQSenderFactory);
				}
				if(mqConfig.getJmsType().compareTo(JmsType.KAFKA)==0){
					KafkaSenderFactory kafkaSenderFactory = new KafkaSenderFactory();
					FACTORY_MAP.put(JmsType.KAFKA,kafkaSenderFactory);
				}
			}
		} catch (Exception e) {
			
		}finally{
			LOCK.unlock();
		}
		return FACTORY_MAP.get(mqConfig.getJmsType()).getJmsSender(mqConfig);
	}
	
	public void setFactoryMap(Map<JmsType,IJmsSenderFactory> factroyMap ){
		FACTORY_MAP = factroyMap;
	}
}

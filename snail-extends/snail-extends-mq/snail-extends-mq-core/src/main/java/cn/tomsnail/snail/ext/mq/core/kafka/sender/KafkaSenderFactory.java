package cn.tomsnail.snail.ext.mq.core.kafka.sender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.tomsnail.snail.ext.mq.core.IJmsSender;
import cn.tomsnail.snail.ext.mq.core.IJmsSenderFactory;
import cn.tomsnail.snail.ext.mq.core.MQConfig;



/**
 *        kafka发送者工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月8日 下午2:04:06
 * @see 
 */
public class KafkaSenderFactory implements IJmsSenderFactory{

	private static final Map<String,KafkaProducer> Producer_Map =  new ConcurrentHashMap<String, KafkaProducer>();
	
	@Override
	public IJmsSender getJmsSender(MQConfig mqConfig) {
		String key = mqConfig.getUrl();
		if(Producer_Map.containsKey(key)){
		}else{
			KafkaProducer kafkaProducer = new KafkaProducer(mqConfig);
			Producer_Map.put(key, kafkaProducer);
		}
		
		return Producer_Map.get(key);
	}

}

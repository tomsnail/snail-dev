package cn.tomsnail.mq.rabbitmq;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RabbitmqFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitmqFactory.class);

	
	private static final Map<String,RabbitmqClient> MAP = new ConcurrentHashMap<String, RabbitmqClient>();
	
	public static synchronized RabbitmqClient get(RabbitmqObject rabbitmqObject){
		
		String uuid = rabbitmqObject.getUUID();
		
		if(!MAP.containsKey(uuid)){
			try {
				RabbitmqClient rabbitmqClient = new RabbitmqClient();
				if(rabbitmqClient.init(rabbitmqObject)){
					MAP.put(uuid, rabbitmqClient);
				}
			} catch (Exception e) {
				logger.error("", e);
				return null;
			}
		}
		return MAP.get(uuid);
	}

}

//package cn.tomsnail.redis.jms;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.data.redis.core.RedisTemplate;
//
//import cn.tomsnail.jms.IJmsSender;
//import cn.tomsnail.jms.IJmsSenderFactory;
//import cn.tomsnail.jms.MQConfig;
//
///**
// *        redis消息发送者工厂
// * @author yangsong
// * @version 0.0.1
// * @status 正常
// * @date 2016年8月18日 上午11:07:39
// * @see 
// */
//public class RedisMQSenderFactory extends RedisBasicFactory implements IJmsSenderFactory{
//	
//	private static Map<RedisTemplate<String,Object>,RedisMQProducer> REDIS_MAP = new HashMap<RedisTemplate<String,Object>, RedisMQProducer>();
//	
//	@Override
//	public IJmsSender getJmsSender(MQConfig mqConfig) {
//		if(mqConfig==null||mqConfig.getUrl()==null){
//			return null;
//		}
//		RedisTemplate<String, Object> redisTemplate = getRedisTemplate(mqConfig);
//		if(redisTemplate==null){
//			return null;
//		}
//		if(!REDIS_MAP.containsKey(redisTemplate)){
//			RedisMQProducer redisMQProducer = new RedisMQProducer();
//			redisMQProducer.setRedisTemplate(redisTemplate);
//			REDIS_MAP.put(redisTemplate, redisMQProducer);
//		}
//		return REDIS_MAP.get(redisTemplate);
//	}
//	
//}

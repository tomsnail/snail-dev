//package cn.tomsnail.redis.jms;
//
//
//
//import org.springframework.data.redis.core.RedisTemplate;
//
//import cn.tomsnail.jms.IConsumerClient;
//import cn.tomsnail.jms.IJmsConsumerFactory;
//import cn.tomsnail.jms.MQConfig;
//
///**
// *        redis 消费者工厂
// * @author yangsong
// * @version 0.0.1
// * @status 正常
// * @date 2016年8月18日 上午11:53:52
// * @see 
// */
//public class RedisMQConsumerFactory extends RedisBasicFactory implements IJmsConsumerFactory{
//
//	@Override
//	public IConsumerClient getConsumerClient(MQConfig mqConfig) {
//		if(mqConfig==null||mqConfig.getUrl()==null){
//			return null;
//		}
//		RedisTemplate<String, Object> redisTemplate = getRedisTemplate(mqConfig);
//		if(redisTemplate==null){
//			return null;
//		}
//		RedisMQClient redisMQClient = new RedisMQClient();
//		redisMQClient.setRedisTemplate(redisTemplate);
//		return redisMQClient;
//	}
//	
//}

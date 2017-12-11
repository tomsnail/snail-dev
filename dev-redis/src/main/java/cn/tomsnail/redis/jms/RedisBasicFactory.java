//package cn.tomsnail.redis.jms;
//
//import org.springframework.data.redis.core.RedisTemplate;
//
//import cn.tomsnail.jms.MQConfig;
//import cn.tomsnail.redis.core.RedisConfig;
//import cn.tomsnail.redis.core.RedisFactory;
//
///**
// *        RedisBasicFactory
// * @author yangsong
// * @version 0.0.1
// * @status 正常
// * @date 2016年8月18日 上午11:58:13
// * @see 
// */
//public class RedisBasicFactory {
//	
//	private RedisFactory redisFactory = new RedisFactory();
//		
//	protected RedisTemplate<String, Object> getRedisTemplate(MQConfig mqConfig) {
//		RedisConfig redisConfig = new RedisConfig();
//		redisConfig.setUrl(mqConfig.getUrl());
//		redisConfig.setPassword(mqConfig.getPassword());
//		RedisTemplate<String, Object> redisTemplate  = redisFactory.getRedisTemplate(redisConfig);
//		return redisTemplate;
//	}
//
//	public RedisFactory getRedisFactory() {
//		return redisFactory;
//	}
//
//	public void setRedisFactory(RedisFactory redisFactory) {
//		this.redisFactory = redisFactory;
//	}
//	
//	
//	
//}

//package cn.tomsnail.redis.jms;
//
//import java.io.Serializable;
//
//import org.springframework.data.redis.core.RedisTemplate;
//
//import cn.tomsnail.jms.IJmsSender;
//
///**
// *        redis生成者
// * @author yangsong
// * @version 0.0.1
// * @status 正常
// * @date 2016年8月17日 下午1:19:53
// * @see 
// */
//public class RedisMQProducer implements IJmsSender,Runnable{
//
//	private RedisTemplate<String, Object> redisTemplate;
//	
//	private boolean isTopic = false;
//	
//	public RedisMQProducer(){
//		
//	}
//	
//	public RedisMQProducer(RedisTemplate<String, Object> redisTemplate){
//		this.redisTemplate = redisTemplate;
//	}
//
//	@Override
//	public void run() {
//		
//	}
//
//	@Override
//	public void send(String topic, Serializable obj) {
//		if(isTopic){
//			redisTemplate.convertAndSend(topic, obj);
//		}else{
//			redisTemplate.opsForList().leftPush(topic, obj);
//		}
//	}
//
//	@Override
//	public void send(String topic, String msg) {
//		if(isTopic){
//			redisTemplate.convertAndSend(topic, msg);
//		}else{
//			redisTemplate.opsForList().leftPush(topic, msg);
//		}
//	}
//
//	public RedisTemplate<String, Object> getRedisTemplate() {
//		return redisTemplate;
//	}
//
//	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
//		this.redisTemplate = redisTemplate;
//	}
//	
//}

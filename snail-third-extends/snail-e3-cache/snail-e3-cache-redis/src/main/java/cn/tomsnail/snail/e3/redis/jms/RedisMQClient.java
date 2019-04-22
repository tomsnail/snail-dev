//package cn.tomsnail.redis.jms;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.data.redis.core.RedisTemplate;
//
//import cn.tomsnail.jms.IConsumerClient;
//import cn.tomsnail.jms.IJmsReceiveCall;
//import cn.tomsnail.jms.MQConfig;
//
///**
// *        Redis MQ 消费者客户端
// * @author yangsong
// * @version 0.0.1
// * @status 正常
// * @date 2016年8月17日 下午2:36:12
// * @see 
// */
//public class RedisMQClient implements IConsumerClient {
//	
//	/**
//	 * 消费者集合
//	 */
//	private List<RedisMQConsumer> consumers = new ArrayList<RedisMQConsumer>();
//	
//	/**
//	 * RedisTemplate
//	 */
//	private RedisTemplate<String,Object> redisTemplate ;
//
//	private MQConfig mqConfig;
//	
//	@Override
//	public void register(IJmsReceiveCall jmsReceiveCall) {
//		register(mqConfig,jmsReceiveCall);
//	}
//
//	@Override
//	public void close() {
//		
//	}
//
//	@Override
//	public void init() {
//		
//	}
//
//	@Override
//	public void register(MQConfig mqConfig, IJmsReceiveCall jmsReceiveCall) {
//		if(mqConfig==null||jmsReceiveCall==null){
//			return;
//		}
//		RedisMQConfig redisMQConfig = (RedisMQConfig) mqConfig;
//		RedisMQConsumer mqConsumer = new RedisMQConsumer(redisMQConfig);
//		mqConsumer.setRedisTemplate(redisTemplate);
//		consumers.add(mqConsumer);
//		mqConsumer.setJmsReceiveCall(jmsReceiveCall);
//		new Thread(mqConsumer).start();
//	}
//
//	public List<RedisMQConsumer> getConsumers() {
//		return consumers;
//	}
//
//	public void setConsumers(List<RedisMQConsumer> consumers) {
//		this.consumers = consumers;
//	}
//
//	public MQConfig getMqConfig() {
//		return mqConfig;
//	}
//
//	public void setMqConfig(MQConfig mqConfig) {
//		this.mqConfig = mqConfig;
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
//	
//}

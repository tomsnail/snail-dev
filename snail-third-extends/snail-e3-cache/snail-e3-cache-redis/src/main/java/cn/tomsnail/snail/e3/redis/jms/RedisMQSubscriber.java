//package cn.tomsnail.redis.jms;
//
//
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import cn.tomsnail.jms.IJmsReceiveCall;
//
///**
// *        redis消息订阅者
// * @author yangsong
// * @version 0.0.1
// * @status 正常
// * @date 2016年9月21日 下午5:24:07
// * @see 
// */
//public class RedisMQSubscriber implements MessageListener{
//	
//	private RedisTemplate<String, Object> redisTemplate;  
//						
//	private IJmsReceiveCall jmsReceiveCall;
//	
//	@Override
//	public void onMessage(Message message, byte[] pattern) {
//		if(jmsReceiveCall!=null){
//			jmsReceiveCall.call(new cn.tomsnail.jms.Message(redisTemplate.getValueSerializer().deserialize(message.getBody())));
//		}
//
//	}
//
//}

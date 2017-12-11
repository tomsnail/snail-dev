//package cn.tomsnail.redis.jms;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import cn.tomsnail.jms.IJmsConsumer;
//import cn.tomsnail.jms.IJmsReceiveCall;
//import cn.tomsnail.jms.IJmsReceiver;
//import cn.tomsnail.jms.Message;
//
///**
// *        redis 消息消费者
// * @author yangsong
// * @version 0.0.1
// * @status 正常
// * @date 2016年8月17日 下午1:19:49
// * @see 
// */
//public class RedisMQConsumer implements IJmsReceiver,Runnable,IJmsConsumer{
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(RedisMQConsumer.class);
//	
//	/**
//	 * redis连接器
//	 */
//	private RedisTemplate<String, Object> redisTemplate;
//	
//	/**
//	 * 是否活动
//	 */
//	private boolean isActived = true;
//	
//	/**
//	 * 超时
//	 */
//	private long timeout = 100l;
//	
//	/**
//	 * 默认队列名称
//	 */
//	private String name = "mqc";
//		
//	/**
//	 * jms回调者
//	 */
//	private IJmsReceiveCall jmsReceiveCall;
//	
//	/**
//	 * 线程池
//	 */
//	private ExecutorService executorService =  Executors.newScheduledThreadPool(5);
//	
//	/**
//	 * redis消息模型
//	 */
//	private RedisMQConfig mqConfig;
//	
//	public RedisMQConsumer(RedisMQConfig mqConfig){
//		this.mqConfig = mqConfig;
//	}
//	
//	@Override
//	public void run() {
//		while(isActived){
//			try {
//				Object obj = redisTemplate.boundListOps(mqConfig.getName()).rightPop(timeout, TimeUnit.MILLISECONDS	);
//				if(timeout==0){
//					try {
//						Thread.currentThread().sleep(100);
//					} catch (InterruptedException e) {
//					}
//				}
//				if(obj!=null){
//					onMessage(new Message(obj));
//				}
//			} catch (Exception e) {
//				LOGGER.error("", e);
//			}
//		}
//	}
//
//
//	@Override
//	public void onMessage(final Message message) {
//		if(jmsReceiveCall!=null){
//			Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
//				@Override
//				public Boolean call() throws Exception {
//					try {
//						jmsReceiveCall.call(message);
//						return true;
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					return false;
//				}
//			});
//			try {
//				future.get(timeout*1000, TimeUnit.MILLISECONDS);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			} catch (TimeoutException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//	public RedisTemplate<String, Object> getRedisTemplate() {
//		return redisTemplate;
//	}
//
//
//	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
//		this.redisTemplate = redisTemplate;
//	}
//
//
//	public boolean isActived() {
//		return isActived;
//	}
//
//
//	public void setActived(boolean isActived) {
//		this.isActived = isActived;
//	}
//
//
//	public long getTimeout() {
//		return timeout;
//	}
//
//
//	public void setTimeout(long timeout) {
//		this.timeout = timeout;
//	}
//
//
//	public String getName() {
//		return name;
//	}
//
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public IJmsReceiveCall getJmsReceiveCall() {
//		return jmsReceiveCall;
//	}
//
//	public void setJmsReceiveCall(IJmsReceiveCall jmsReceiveCall) {
//		this.jmsReceiveCall = jmsReceiveCall;
//	}
//
//	public ExecutorService getExecutorService() {
//		return executorService;
//	}
//
//	public void setExecutorService(ExecutorService executorService) {
//		this.executorService = executorService;
//	}
//
//	public RedisMQConfig getMqConfig() {
//		return mqConfig;
//	}
//
//	public void setMqConfig(RedisMQConfig mqConfig) {
//		this.mqConfig = mqConfig;
//	}
//
//	@Override
//	public void init() {
//		
//	}
//
//
//	@Override
//	public void start() {
//		
//	}
//
//
//	@Override
//	public void close() {
//		
//	}
//	
//	
//}

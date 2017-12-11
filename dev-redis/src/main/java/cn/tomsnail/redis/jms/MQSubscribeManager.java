//package cn.tomsnail.redis.jms;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import cn.tomsnail.jms.ISubscribeManager;
//import cn.tomsnail.jms.IJmsReceiveCall;
//import cn.tomsnail.jms.MQConfig;
//import cn.tomsnail.jms.Message;
//
///**
// *        redis订阅管理者
// * @author yangsong
// * @version 0.0.1
// * @status 正常
// * @date 2016年8月17日 下午1:35:04
// * @see 
// */
///**
// *        
// * @author yangsong
// * @version 0.0.1
// * @status 正常
// * @date 2016年9月21日 下午5:19:48
// * @see 
// */
//public class MQSubscribeManager implements IJmsReceiveCall,ISubscribeManager{
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(MQSubscribeManager.class);
//	
//	/**
//	 * 接收回调列表
//	 */
//	private List<IJmsReceiveCall> jmsReceiveCalls = new ArrayList<IJmsReceiveCall>(); 
//	
//	private ExecutorService executorService ;
//
//	@Override
//	public void call(final Message msg) {
//		if(jmsReceiveCalls!=null){
//			for(final IJmsReceiveCall jmsReceiveCall:this.jmsReceiveCalls){
//				try {
//					getExecutorService().execute(new Runnable() {
//						@Override
//						public void run() {
//							jmsReceiveCall.call(msg);
//						}
//					});
//				} catch (Exception e) {
//					LOGGER.error("", e);
//				}
//			}
//		}
//	}
//	
//	/**
//	 *        线程池服务
//	 * @methodauthor yangsong
//	 * @methodversion 0.0.1
//	 * @date 2016年9月21日 下午5:20:55
//	 * @see 
//	 * @param                   
//	 * @return               
//	 * @status 正常
//	 * @exception no
//	 */
//	private ExecutorService getExecutorService(){
//		if(executorService==null){
//			synchronized (MQSubscribeManager.class) {
//				if(executorService==null){
//					executorService = Executors.newFixedThreadPool(jmsReceiveCalls.size()==0?jmsReceiveCalls.size():5);
//				}
//			}
//		}
//		return executorService;
//	}
//
//	@Override
//	public void register(IJmsReceiveCall jmsReceiveCall) {
//		if(jmsReceiveCall!=null){
//			jmsReceiveCalls.add(jmsReceiveCall);
//		}
//	}
//
//	@Override
//	public void close() {
//		
//	}
//
//	@Override
//	public void init() {
//		executorService = Executors.newFixedThreadPool(jmsReceiveCalls.size()==0?jmsReceiveCalls.size():5);
//	}
//
//	@Override
//	public void register(MQConfig mqConfig, IJmsReceiveCall jmsReceiveCall) {
//		
//	}
//
//	
//}

package cn.tomsnail.snail.ext.mq.core.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.StringUtils;

import cn.tomsnail.snail.ext.mq.core.IJmsConsumer;
import cn.tomsnail.snail.ext.mq.core.IJmsReceiveCall;
import cn.tomsnail.snail.ext.mq.core.IJmsReceiver;
import cn.tomsnail.snail.ext.mq.core.Message;
import cn.tomsnail.snail.ext.mq.core.kafka.KafkaMQConfig;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月8日 上午11:02:30
 * @see 
 */
public class KafkaConsumer implements IJmsReceiver, Runnable, IJmsConsumer {

	/**
	 * 消费者连接点
	 */
	private ConsumerConnector consumer;

	/**
	 * 是否活动
	 */
	private boolean isActived = true;

	/**
	 * 超时时间
	 */
	private long timeout = 100l;

	/**
	 * 默认消息队列名称
	 */
	private String name = "mqc";

	/**
	 * 消息接收者回调
	 */
	private IJmsReceiveCall jmsReceiveCall;

	/**
	 * 线程池
	 */
	private ExecutorService executorService = null;

	/**
	 * 消息模型
	 */
	private KafkaMQConfig mqConfig;
	
	public KafkaConsumer(KafkaMQConfig mqConfig){
		this.mqConfig = mqConfig;
		executorService = Executors.newFixedThreadPool(mqConfig.getThreadNum());
		consumer = this.createConsumer();
		this.name = mqConfig.getName();
	}

	@Override
	public void init() {
		
	}

	@Override
	public void start() {

	}

	@Override
	public void close() {
		if(consumer!=null)
			consumer.shutdown();
		if(executorService!=null)
			executorService.shutdown();
	}

	@Override
	public void run() {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(name, 1); // 一次从主题中获取一个数据
		Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(topicCountMap);
		KafkaStream<byte[], byte[]> stream = messageStreams.get(name).get(0);// 获取每次接收到的这个数据
		ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
		while (isActived && iterator.hasNext()) {
			String message = new String(iterator.next().message());
			onMessage(new Message(message));
		}
	}

	@Override
	public void onMessage(final Message message) {
		if (executorService!=null&&jmsReceiveCall != null) {
			Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
						@Override
						public Boolean call() throws Exception {
							try {
								jmsReceiveCall.call(message);
								return true;
							} catch (Exception e) {
								e.printStackTrace();
							}
							return false;
						}
					});
			try {
				future.get(timeout * 1000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *        创建消费者
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:14:55
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	private ConsumerConnector createConsumer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", mqConfig.getUrl());// 声明zk
		if(!StringUtils.isBlank(mqConfig.getGroup())){
			properties.put("group.id", "group" + mqConfig.getGroup());
		}else{
			properties.put("group.id", "group" + System.currentTimeMillis());
		}
		return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
	}

	public ConsumerConnector getConsumer() {
		return consumer;
	}

	public void setConsumer(ConsumerConnector consumer) {
		this.consumer = consumer;
	}

	public boolean isActived() {
		return isActived;
	}

	public void setActived(boolean isActived) {
		this.isActived = isActived;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IJmsReceiveCall getJmsReceiveCall() {
		return jmsReceiveCall;
	}

	public void setJmsReceiveCall(IJmsReceiveCall jmsReceiveCall) {
		this.jmsReceiveCall = jmsReceiveCall;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public KafkaMQConfig getMqConfig() {
		return mqConfig;
	}

	public void setMqConfig(KafkaMQConfig mqConfig) {
		this.mqConfig = mqConfig;
	}

	
}

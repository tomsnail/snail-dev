package cn.tomsnail.snail.ext.mq.core.mq.activemq;

import java.util.concurrent.LinkedBlockingQueue;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

/**
 *        ActiveMQ消息产生者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年1月19日 下午3:34:47
 * @see 
 */
public class ActiveMQProducer extends Thread {

	private static final Logger send_logger = Logger.getLogger("SendMessageLogger");

	// 发送目的地
	private Destination destination;
	//
	private boolean verbose = true;
	// 生存时间
	private long timeToLive;
	// 用户名
	private String user = ActiveMQConnection.DEFAULT_USER;
	// 密码
	private String password = ActiveMQConnection.DEFAULT_PASSWORD;
	// mq的链接URL
	private String url = "tcp://192.168.2.2:61616";
	// 发送主题
	private String subject = "TOOL.TOOSKII1";
	// 是否采用主题形式
	private boolean topic = true;
	private boolean transacted;
	private boolean persistent;

	private MessageProducer producer;
	private Connection connection = null;
	private Session session = null;
	
	private LinkedBlockingQueue<String> queue;

	public ActiveMQProducer(String url, String subject,LinkedBlockingQueue<String> queue,boolean topic) {
		this.url = url;
		this.subject = subject;
		this.queue = queue;
		this.topic = topic;
		createConnection();

	}

	private void createConnection() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, password, url);
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			// Create the session
			session = connection.createSession(transacted,
					Session.AUTO_ACKNOWLEDGE);
			  if (topic) {
	                destination = session.createTopic(subject);
	            } else {
	                destination = session.createQueue(subject);
	            }
			// Create the producer.
			producer = session.createProducer(destination);
			if (persistent) {
				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			} else {
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			}
			if (timeToLive != 0) {
				producer.setTimeToLive(timeToLive);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void showParameters() {
		if (timeToLive != 0) {
			
		}
	}

	
	public void run() {
		if(queue!=null){
			while(true){
				try {
					String msg = queue.take();
					if(msg!=null){
						send(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 发送消息
	 * 
	 * @author yangsong
	 * @date 2014年5月12日 上午11:05:16
	 */
	public void send(String msgStr) throws Exception {
		sendLoop(session, producer, msgStr);
	}

	protected void sendLoop(Session session, MessageProducer producer,
			String msgStr) throws Exception {
		if (verbose) {
			try {
				TextMessage message = session.createTextMessage(msgStr);
				if (send_logger.isDebugEnabled()) {
					String msg = message.getText();
					send_logger.debug(msg);
				}
				producer.send(message);
			} catch (Exception e) {
				String error = e.getMessage();
				if (error.contains("The Session is closed")) {
					reInit();
					send(msgStr);
				} else {
					e.printStackTrace();
				}
			}
		}
	}

	private void reInit() {
		try {
			connection.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		connection = null;
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, password, url);
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			// Create the session
			session = connection.createSession(transacted,
					Session.AUTO_ACKNOWLEDGE);
			  if (topic) {
	                destination = session.createTopic(subject);
	            } else {
	                destination = session.createQueue(subject);
	            }
			// Create the producer.
			producer = session.createProducer(destination);
			if (persistent) {
				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			} else {
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			}
			if (timeToLive != 0) {
				producer.setTimeToLive(timeToLive);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPersistent(boolean durable) {
		this.persistent = durable;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	public void setTopic(boolean topic) {
		this.topic = topic;
	}

	public void setQueue(boolean queue) {
		this.topic = !queue;
	}

	public void setTransacted(boolean transacted) {
		this.transacted = transacted;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public String getUrl() {
		return this.url;
	}

	public String getSubject() {
		return this.subject;
	}

}

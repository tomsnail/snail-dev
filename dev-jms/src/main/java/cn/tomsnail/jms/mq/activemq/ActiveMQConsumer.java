package cn.tomsnail.jms.mq.activemq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *        actviemq消费者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年1月19日 下午3:30:47
 * @see 
 */
public class ActiveMQConsumer  extends Thread implements MessageListener, ExceptionListener{
	private static final Logger logger = LoggerFactory.getLogger(ActiveMQConsumer.class);
    /** 是否还运行着*/
	private boolean running;

    private Session session;
    private Destination destination;
    private MessageProducer replyProducer;
    MessageConsumer consumer = null;
    /** 中止之前是否关闭*/
    private boolean pauseBeforeShutdown = false;
    /** 原来这个是多余的意思*/
    private boolean verbose = true;
    /** 最大消息数*/
    private int maxiumMessages;
    private String subject = "";
    private boolean topic = true;
    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = "";
    private boolean transacted = false;
    private boolean durable = false;
    private String clientId;
    private int ackMode = Session.AUTO_ACKNOWLEDGE;
    private String consumerName = "James";
    private long sleepTime = 0;
    private long receiveTimeOut;
	private long batch = 1; 
	private long messagesReceived = 0;
    private Connection connection;
    
    private JmsReceiveHandler jmsReceiveHandler;
    
	public ActiveMQConsumer(String subject,String url,String consumerName,boolean topic){
		this.url=url;
		this.subject=subject;
		this.consumerName=consumerName;
		this.topic = topic;
		if(this.topic){
			clientId = Thread.currentThread().getName();
		}
		init();
	}
	
	
	
	
    public static void main(String[] args) {
  
 
    }
    
    private void init(){
    	 ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, this.url+"?jms.useAsyncSend=true");
         try {
			connection = connectionFactory.createConnection();
			   if (durable && clientId != null && clientId.length() > 0 && !"null".equals(clientId)) {
	                connection.setClientID(consumerName);
	            }
	            
	            connection.setExceptionListener(this);
	            connection.start();	            
	            session = connection.createSession(transacted, ackMode);
	          
	            if (topic) {
	                destination = session.createTopic(this.subject);
	            } else {
	                destination = session.createQueue(this.subject);
	            }

	            replyProducer = session.createProducer(null);
	            replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

	            if (durable && topic) {
	                consumer = session.createDurableSubscriber((Topic) destination, this.consumerName);
	            } else {
	                consumer = session.createConsumer(destination);
	            }
		} catch (JMSException e) {
			e.printStackTrace();
			running = false;
		}
    }
    
    //这个是后备方法，当工具类未知原因的死亡后，才有listener启动
    public void run() {
        try {
            running = true;
            if (maxiumMessages > 0) {
                consumeMessagesAndClose();
            } else {
                if (receiveTimeOut == 0) {
                    consumer.setMessageListener(this);
                } else {
                    consumeMessagesAndClose(connection, session, consumer, receiveTimeOut);
                }
            }

        } catch (Exception e) {
            logger.info("[" + this.getName() + "] Caught: " + e);
            e.printStackTrace();
            running = false;
        }
    }
    
    public void exit(){
    	running = false;
    }
    
    /**
	 * 处理消息吧
	 * @param
	 * @return
	 * @author yangsong
	 * @date 2014年5月12日 下午3:11:54
	 */
    public void onMessage(Message message) {
		messagesReceived++;
        try {
            if (message instanceof TextMessage) {
                TextMessage txtMsg = (TextMessage) message;
                if (verbose) {
                	
            		jmsReceiveHandler.call(new cn.tomsnail.jms.Message(txtMsg.getText()));
                }
            } else {
                if (verbose) {
                    logger.info("[" + this.getName() + "] Received: '" + message + "'");
                }
            }
            if (message.getJMSReplyTo() != null) {
                replyProducer.send(message.getJMSReplyTo(), session.createTextMessage("Reply: " + message.getJMSMessageID()));
            }

            if (transacted) {
				if ((messagesReceived % batch) == 0) {
					session.commit();
				}
            } else if (ackMode == Session.CLIENT_ACKNOWLEDGE) {
				if ((messagesReceived % batch) == 0) {
					System.out.println("Acknowledging last " + batch + " messages; messages so far = " + messagesReceived);
					message.acknowledge();
				}
            }
        } catch (JMSException e) {
            logger.info("[" + this.getName() + "] Caught: " + e);
            e.printStackTrace();
        } finally {

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public synchronized void onException(JMSException ex) {
        logger.info("[" + this.getName() + "] JMS Exception occured.  Shutting down client.");
        running = false;
    }

    synchronized boolean isRunning() {
        return running;
    }
    /**
	 * 获得消息，然后关闭连接
	 * @params
	 * Connection connection
	 * Session session
	 * MessageConsumer consumer
	 * @return
	 * @author yangsong
	 * @date 2014年5月12日 下午3:10:47
	 */
    protected void consumeMessagesAndClose(Connection connection, Session session, MessageConsumer consumer) throws JMSException,
            IOException {
        logger.info("[" + this.getName() + "] We are about to wait until we consume: " + maxiumMessages
                + " message(s) then we will shutdown");

        for (int i = 0; i < maxiumMessages && isRunning();) {
            Message message = consumer.receive(1000);
            if (message != null) {
                i++;
                onMessage(message);
            }
        }
        logger.info("[" + this.getName() + "] Closing connection");
        consumer.close();
        session.close();
        connection.close();
        if (pauseBeforeShutdown) {
            logger.info("[" + this.getName() + "] Press return to shut down");
            System.in.read();
        }
    }
    /**
	 * 获得消息，然后关闭连接
	 * @params
	 * Connection connection, Session session, MessageConsumer consumer, long timeout
	 * @return
	 * @author yangsong
	 * @date 2014年5月12日 下午3:10:47
	 */
    protected void consumeMessagesAndClose(Connection connection, Session session, MessageConsumer consumer, long timeout)
            throws JMSException, IOException {
        logger.info("[" + this.getName() + "] We will consume messages while they continue to be delivered within: " + timeout
                + " ms, and then we will shutdown");

        Message message;
        while ((message = consumer.receive(timeout)) != null&&running) {
            onMessage(message);
        }

        logger.info("[" + this.getName() + "] Closing connection");
        consumer.close();
        session.close();
        connection.close();
        if (pauseBeforeShutdown) {
            logger.info("[" + this.getName() + "] Press return to shut down");
            System.in.read();
        }
    }
    /**
	 * 获得消息，然后关闭连接，这个不带任何参数
	 * @param
	 * @return
	 * @author yangsong
	 * @date 2014年5月12日 下午3:10:47
	 */
	    public void consumeMessagesAndClose( ) throws JMSException,
	    IOException {
	logger.debug("[" + this.getName() + "] We are about to wait until we consume: " + maxiumMessages
	        + " message(s) then we will shutdown");
	
	for (int i = 0; i < maxiumMessages && isRunning();) {
	    Message message = consumer.receive(1000);
	    if (message != null) {
	        i++;
	        onMessage(message);
	    }
	}
	logger.debug("[" + this.getName() + "] Closing connection");
	consumer.close();
	session.close();
	connection.close();
	if (pauseBeforeShutdown) {
	    logger.debug("[" + this.getName() + "] Press return to shut down");
	    System.in.read();
	}
	}
    public void setAckMode(String ackMode) {
        if ("CLIENT_ACKNOWLEDGE".equals(ackMode)) {
            this.ackMode = Session.CLIENT_ACKNOWLEDGE;
        }
        if ("AUTO_ACKNOWLEDGE".equals(ackMode)) {
            this.ackMode = Session.AUTO_ACKNOWLEDGE;
        }
        if ("DUPS_OK_ACKNOWLEDGE".equals(ackMode)) {
            this.ackMode = Session.DUPS_OK_ACKNOWLEDGE;
        }
        if ("SESSION_TRANSACTED".equals(ackMode)) {
            this.ackMode = Session.SESSION_TRANSACTED;
        }
    }

    public void setClientId(String clientID) {
        this.clientId = clientID;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public void setMaxiumMessages(int maxiumMessages) {
        this.maxiumMessages = maxiumMessages;
    }

    public void setPauseBeforeShutdown(boolean pauseBeforeShutdown) {
        this.pauseBeforeShutdown = pauseBeforeShutdown;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public void setReceiveTimeOut(long receiveTimeOut) {
        this.receiveTimeOut = receiveTimeOut;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public void setBatch(long batch) {
        this.batch = batch;
    }



	public JmsReceiveHandler getJmsReceiveHandler() {
		return jmsReceiveHandler;
	}



	public void setJmsReceiveHandler(JmsReceiveHandler jmsReceiveHandler) {
		this.jmsReceiveHandler = jmsReceiveHandler;
	}
    
    
}

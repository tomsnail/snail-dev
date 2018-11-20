package cn.tomsnail.mq.rabbitmq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.util.string.StringUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class RabbitmqClient implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitmqClient.class);

	Connection connection = null;
	 
	Channel channel = null;
	
	List<RabbitMQCustomer> rabbitMQCustomers = new ArrayList<RabbitMQCustomer>();
	
	
	
	
	protected boolean initd = true;
	

	
	RabbitmqObject ro = null;
	

	
	
	public boolean init(RabbitmqObject ro) throws Exception{
		
		
		if(ro==null||StringUtils.isAnyBlank(ro.ip,ro.username,ro.password)){
			return false;
		}
		
		this.ro = ro;
		
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ro.ip);
        factory.setUsername(ro.username);
        factory.setVirtualHost(ro.vHost);
        factory.setPassword(ro.password);
        factory.setPort(ro.port);
        
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(ro.exchangeName, ro.type, ro.eDurable, ro.eAutoDelete, null);
        channel.queueDeclare(ro.queueName, ro.qDurable, ro.qExclusive, ro.qAutoDelete, null);
        channel.queueBind(ro.queueName, ro.exchangeName, ro.routeKey, null);
  
        
        
        run();
        
        return initd = true;
	}
	
	
	
	
	public boolean register(RabbitMQCustomer rabbitMQCustomer,boolean reConnect) throws Exception{
		logger.debug("rabbitmq register start");
		
		if(rabbitMQCustomer==null){
			return false;
		}
		
		if(!reConnect) {
			if(!initd){
				return false;
			}
			
			if(rabbitMQCustomers.contains(rabbitMQCustomer)){
				return true;
			}
			
			rabbitMQCustomers.add(rabbitMQCustomer);
		}
		
		
		
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)throws IOException {
                String message = new String(body, "UTF-8");
                logger.debug("rabbitmq msg is : {}",message);
                rabbitMQCustomer.handler(message);
            }
        };
        channel.basicConsume(ro.queueName, ro.autoAck, consumer);
        logger.debug("rabbitmq register end");
        return true;
	}
	
	protected void registerAll(){
		logger.debug("rabbitmq register start");
		if(rabbitMQCustomers==null||rabbitMQCustomers.isEmpty()){
			logger.debug("rabbitmq rabbitMQCustomers is null");
			return;
		}
		
		for(RabbitMQCustomer rabbitMQCustomer:rabbitMQCustomers){
			try {
				register(rabbitMQCustomer,true);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		logger.debug("rabbitmq register end");
		
	}
	
	public void send(String message) throws Exception {
		if(initd){
			 channel.basicPublish(ro.exchangeName, ro.routeKey, null, message.getBytes("UTF-8"));
		}
	}

	@Override
	public void run() {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.currentThread().sleep(20000l);
					} catch (InterruptedException e) {
						logger.error("", e);
					}
					logger.debug("rabbitmq check start");
					if(ro.isReconnect&&initd){
						logger.debug("rabbitmq is inited");
						if(connection==null||channel==null){
							logger.debug("rabbitmq is not connect");
							try {
								close();
								registerAll();
							} catch (Exception e) {
								logger.error("", e);
							}
						}else{
							logger.debug("rabbitmq has connected");
							if(connection.isOpen()&&channel.isOpen()){
								logger.debug("rabbitmq open now");
							}else{
								logger.debug("rabbitmq has colse");
								try {
									close();
									registerAll();
								} catch (Exception e) {
									logger.error("", e);
								}
							}
						}
					}
					logger.debug("rabbitmq check end");
					
				}
				
			}
		}).start();
		
	}
	
	public void close(){
		logger.debug("rabbitmq colse start");
		if(channel!=null){
			try {
				channel.close();
			} catch (IOException | TimeoutException e) {
				logger.error("", e);
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		logger.debug("rabbitmq colse end");
	}

}

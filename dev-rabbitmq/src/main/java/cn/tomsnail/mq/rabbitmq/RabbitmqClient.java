package cn.tomsnail.mq.rabbitmq;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
		
		
		if(ro==null||StringUtils.isAnyBlank(ro.exchangeName,ro.queueName,ro.vHost,ro.ip,ro.username,ro.password)){
			return false;
		}
		
		this.ro = ro;
		
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ro.ip);
        factory.setUsername(ro.username);
        factory.setVirtualHost(ro.vHost);
        factory.setPassword(ro.password);
        factory.setPort(ro.port);
        
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(ro.exchangeName, ro.type, ro.eDurable, ro.eAutoDelete, null);
        channel.queueDeclare(ro.queueName, ro.qDurable, ro.qExclusive, ro.qAutoDelete, null);
        channel.queueBind(ro.queueName, ro.exchangeName, ro.routeKey, null);
  
        
        
        new Thread(this).start();
        
        return initd = true;
	}
	
	
	
	
	public boolean register(RabbitMQCustomer rabbitMQCustomer) throws Exception{
		
		
		if(rabbitMQCustomer==null){
			return false;
		}
		
		if(!initd){
			return false;
		}
		
		if(rabbitMQCustomers.contains(rabbitMQCustomer)){
			return true;
		}
		
		rabbitMQCustomers.add(rabbitMQCustomer);
		
		
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)throws IOException {
                String message = new String(body, "UTF-8");
                logger.info("rabbitmq msg is : {}",message);
                rabbitMQCustomer.handler(message);
            }
        };
        channel.basicConsume(ro.queueName, ro.autoAck, consumer);
        return true;
	}
	
	protected void registerAll(){
		if(rabbitMQCustomers==null||rabbitMQCustomers.isEmpty()){
			return;
		}
		
		for(RabbitMQCustomer rabbitMQCustomer:rabbitMQCustomers){
			try {
				register(rabbitMQCustomer);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		
	}
	
	public void send(String message) throws Exception {
		if(initd){
			 channel.basicPublish(ro.exchangeName, ro.routeKey, null, message.getBytes("UTF-8"));
		}
	}

	@Override
	public void run() {
		while(ro.isRun){
			try {
				Thread.currentThread().sleep(20000l);
			} catch (InterruptedException e) {
				logger.error("", e);
			}
			if(ro.isReconnect&&initd){
				if(connection==null||channel==null){
					try {
						close();
						registerAll();
					} catch (Exception e) {
						logger.error("", e);
					}
				}else{
					if(connection.isOpen()&&channel.isOpen()){
						
					}else{
						try {
							close();
							registerAll();
						} catch (Exception e) {
							logger.error("", e);
						}
					}
				}
			}
			
		}
	}
	
	public void close(){
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
		initd = false;
	}

}

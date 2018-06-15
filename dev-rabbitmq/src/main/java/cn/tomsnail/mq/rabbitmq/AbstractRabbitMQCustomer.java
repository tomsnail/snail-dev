package cn.tomsnail.mq.rabbitmq;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import cn.tomsnail.config.client.ConfigClientFactory;
import cn.tomsnail.util.string.StringUtils;


public abstract class AbstractRabbitMQCustomer implements RabbitMQCustomer{
	
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractRabbitMQCustomer.class);
	
	protected String exchangeName;
	
	protected String queueName;
	
	protected String vHost;
	
	protected String ip;
	
	protected String username;
	
	protected String password;
	
	protected String routeKey;
	
	protected int port;
	
	protected boolean isInitd = false;
	
	protected String type;
	
	protected boolean eDurable = true;
	protected boolean eAutoDelete = false;
	
	protected boolean qDurable = true;
	protected boolean qExclusive = false;
	protected boolean qAutoDelete = false;
	
	protected boolean autoAck = true;
	
	
	protected void initProps() throws Exception{
		
		exchangeName = ConfigClientFactory.getConfig("rabbit.exchange.name", "");
		
		type = ConfigClientFactory.getConfig("rabbit.exchange.type", "direct");
		
		queueName = ConfigClientFactory.getConfig("rabbit.queue.name", "");
		
		vHost = ConfigClientFactory.getConfig("rabbit.vhost", "");
		
		ip = ConfigClientFactory.getConfig("rabbit.ip", "");
		
		username = ConfigClientFactory.getConfig("rabbit.uesrname", "");
		
		password = ConfigClientFactory.getConfig("rabbit.password", "");
		
		routeKey = ConfigClientFactory.getConfig("rabbit.route.key", "");
		
		port = Integer.valueOf(ConfigClientFactory.getConfig("rabbit.port", "5672"));
		
		eDurable = Boolean.valueOf(ConfigClientFactory.getConfig("rabbit.exchange.durable", "true"));
		eAutoDelete = Boolean.valueOf(ConfigClientFactory.getConfig("rabbit.exchange.autoDelete", "false"));
		
		qDurable = Boolean.valueOf(ConfigClientFactory.getConfig("rabbit.queue.durable", "true"));
		qExclusive = Boolean.valueOf(ConfigClientFactory.getConfig("rabbit.queue.exclusive", "false"));
		qAutoDelete = Boolean.valueOf(ConfigClientFactory.getConfig("rabbit.queue.autoDelete", "false"));
		
		autoAck = Boolean.valueOf(ConfigClientFactory.getConfig("rabbit.autoAck", "true"));

	}
	
	@PostConstruct
	public void init() throws Exception {
		initProps();
		
		
		if(StringUtils.isAnyBlank(exchangeName,queueName,vHost,ip,username,password)){
			return;
		}
		
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ip);
        factory.setUsername(username);
        factory.setVirtualHost(vHost);
        factory.setPassword(password);
        factory.setPort(port);
        
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, type, eDurable, eAutoDelete, null);
        channel.queueDeclare(queueName, qDurable, qExclusive, qAutoDelete, null);
        channel.queueBind(queueName, exchangeName, routeKey, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)throws IOException {
                String message = new String(body, "UTF-8");
                logger.info("rabbitmq msg is : {}",message);
                handler(message);
            }
        };
        channel.basicConsume(queueName, autoAck, consumer);
        isInitd = true;
        logger.info("rabbitmq init success!");
	}

}

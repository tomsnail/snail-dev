package cn.tomsnail.snail.e3.mq.rabbitmq;


import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.config.client.ConfigClientFactory;
import cn.tomsnail.snail.core.util.string.StringUtils;



@Component
public class RabbitMQSender {
	
	
	protected RabbitmqObject rabbitmqObject = new RabbitmqObject();
	
	@PostConstruct
	public void initProps() throws Exception{
		
		rabbitmqObject.exchangeName = ConfigClientFactory.getConfig("rabbitmq.exchange.name", "");
		
		rabbitmqObject.type = ConfigClientFactory.getConfig("rabbitmq.exchange.type", "direct");
		
		rabbitmqObject.queueName = ConfigClientFactory.getConfig("rabbitmq.queue.name", "");
		
		rabbitmqObject.vHost = ConfigClientFactory.getConfig("rabbitmq.vhost", "");
		
		rabbitmqObject.ip = ConfigClientFactory.getConfig("rabbitmq.ip", "");
		
		rabbitmqObject.username = ConfigClientFactory.getConfig("rabbitmq.uesrname", "");
		
		rabbitmqObject.password = ConfigClientFactory.getConfig("rabbitmq.password", "");
		
		rabbitmqObject.routeKey = ConfigClientFactory.getConfig("rabbitmq.route.key", "");
		
		rabbitmqObject.port = Integer.parseInt(ConfigClientFactory.getConfig("rabbitmq.port", "5672"));
		
		rabbitmqObject.eDurable = Boolean.parseBoolean(ConfigClientFactory.getConfig("rabbitmq.exchange.durable", "true"));
		rabbitmqObject.eAutoDelete = Boolean.parseBoolean(ConfigClientFactory.getConfig("rabbitmq.exchange.autoDelete", "false"));
		
		rabbitmqObject.qDurable = Boolean.parseBoolean(ConfigClientFactory.getConfig("rabbitmq.queue.durable", "true"));
		rabbitmqObject.qExclusive = Boolean.parseBoolean(ConfigClientFactory.getConfig("rabbitmq.queue.exclusive", "false"));
		rabbitmqObject.qAutoDelete = Boolean.parseBoolean(ConfigClientFactory.getConfig("rabbitmq.queue.autoDelete", "false"));
		
		rabbitmqObject.autoAck = Boolean.parseBoolean(ConfigClientFactory.getConfig("rabbitmq.autoAck", "true"));
		
		rabbitmqObject.isReconnect = Boolean.parseBoolean(ConfigClientFactory.getConfig("rabbitmq.isReconnect", "true"));
		
		rabbitmqObject.isRun = Boolean.parseBoolean(ConfigClientFactory.getConfig("rabbitmq.isRun", "true"));

	}
	
	
	
	public void send(String msg) throws Exception{
		if(StringUtils.isBlank(msg)) return;
		RabbitmqClient rabbitmqClient = RabbitmqFactory.get(rabbitmqObject);
		if(rabbitmqClient!=null) rabbitmqClient.send(msg);
	}

}

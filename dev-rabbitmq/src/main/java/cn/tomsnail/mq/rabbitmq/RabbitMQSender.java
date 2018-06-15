package cn.tomsnail.mq.rabbitmq;


import org.springframework.stereotype.Component;

import cn.tomsnail.config.client.ConfigClientFactory;

@Component
public class RabbitMQSender {
	
	
	protected RabbitmqObject rabbitmqObject = new RabbitmqObject();
	
	
	protected void initProps() throws Exception{
		
		rabbitmqObject.exchangeName = ConfigClientFactory.getConfig("rabbitmq.exchange.name", "");
		
		rabbitmqObject.type = ConfigClientFactory.getConfig("rabbitmq.exchange.type", "direct");
		
		rabbitmqObject.queueName = ConfigClientFactory.getConfig("rabbitmq.queue.name", "");
		
		rabbitmqObject.vHost = ConfigClientFactory.getConfig("rabbitmq.vhost", "");
		
		rabbitmqObject.ip = ConfigClientFactory.getConfig("rabbitmq.ip", "");
		
		rabbitmqObject.username = ConfigClientFactory.getConfig("rabbitmq.uesrname", "");
		
		rabbitmqObject.password = ConfigClientFactory.getConfig("rabbitmq.password", "");
		
		rabbitmqObject.routeKey = ConfigClientFactory.getConfig("rabbitmq.route.key", "");
		
		rabbitmqObject.port = Integer.valueOf(ConfigClientFactory.getConfig("rabbitmq.port", "5672"));
		
		rabbitmqObject.eDurable = Boolean.valueOf(ConfigClientFactory.getConfig("rabbitmq.exchange.durable", "true"));
		rabbitmqObject.eAutoDelete = Boolean.valueOf(ConfigClientFactory.getConfig("rabbitmq.exchange.autoDelete", "false"));
		
		rabbitmqObject.qDurable = Boolean.valueOf(ConfigClientFactory.getConfig("rabbitmq.queue.durable", "true"));
		rabbitmqObject.qExclusive = Boolean.valueOf(ConfigClientFactory.getConfig("rabbitmq.queue.exclusive", "false"));
		rabbitmqObject.qAutoDelete = Boolean.valueOf(ConfigClientFactory.getConfig("rabbitmq.queue.autoDelete", "false"));
		
		rabbitmqObject.autoAck = Boolean.valueOf(ConfigClientFactory.getConfig("rabbitmq.autoAck", "true"));
		
		rabbitmqObject.isReconnect = Boolean.valueOf(ConfigClientFactory.getConfig("rabbitmq.isReconnect", "true"));
		
		rabbitmqObject.isRun = Boolean.valueOf(ConfigClientFactory.getConfig("rabbitmq.isRun", "true"));

	}
	
	
	
	public void send(String msg) throws Exception{
		RabbitmqClient rabbitmqClient = RabbitmqFactory.get(rabbitmqObject);
		rabbitmqClient.send(msg);
	}

}

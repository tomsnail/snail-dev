package cn.tomsnail.snail.example.ext.mq.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSendService implements ConfirmCallback{
	
	
	@Autowired
	private RabbitTemplate rabbitTemplate = null;

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			System.out.println("消息成功消费");
		}else {
			System.out.println("消息消费失败:" + cause);
		}
		
	}
	
	public void send(String message) {
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.convertAndSend("rabbitmq.test", "", message);
	}
	
}

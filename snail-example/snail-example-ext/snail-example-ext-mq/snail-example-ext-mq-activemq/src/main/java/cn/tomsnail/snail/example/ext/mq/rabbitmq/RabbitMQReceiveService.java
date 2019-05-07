package cn.tomsnail.snail.example.ext.mq.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiveService {

	@RabbitListener(queues = { "test" })
	public void receiver(String message) {
		System.out.println("收到消息: 【" + message + "】");
	}
	
}

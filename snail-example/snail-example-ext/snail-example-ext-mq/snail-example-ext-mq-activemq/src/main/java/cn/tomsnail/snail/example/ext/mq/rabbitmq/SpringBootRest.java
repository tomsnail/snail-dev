package cn.tomsnail.snail.example.ext.mq.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.tomsnail.snail.core.log.annotation.LogPoint;

@RestController
@RequestMapping("/rabbitmq")
public class SpringBootRest {
	
	@Autowired
	private RabbitMQSendService rabbitMQSendService;

	@LogPoint
	@RequestMapping(value = "{message}", method = RequestMethod.GET)
	public String put(@PathVariable("message") String message) {
		rabbitMQSendService.send(message);
		return "OK";
	}

}

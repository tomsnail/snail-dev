package cn.tomsnail.snail.ext.mq.rabbitmq;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@AutoConfigureAfter(value= {RabbitAutoConfiguration.class})
@PropertySource("classpath:config.properties")
public class AutoRabbitMQConfigurer {
	
}

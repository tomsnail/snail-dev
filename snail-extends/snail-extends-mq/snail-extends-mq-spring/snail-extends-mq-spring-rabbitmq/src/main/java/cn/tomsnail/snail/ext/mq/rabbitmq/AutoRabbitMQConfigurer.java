package cn.tomsnail.snail.ext.mq.rabbitmq;

import cn.tomsnail.snail.core.starter.spring.MixPropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@AutoConfigureAfter(value= {RabbitAutoConfiguration.class})
@PropertySource(value = {"classpath:config.properties","classpath:config.yml"},factory = MixPropertySourceFactory.class)
public class AutoRabbitMQConfigurer {
	
}

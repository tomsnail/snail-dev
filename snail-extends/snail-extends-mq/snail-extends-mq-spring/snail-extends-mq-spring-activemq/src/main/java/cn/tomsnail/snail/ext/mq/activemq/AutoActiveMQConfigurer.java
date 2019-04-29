package cn.tomsnail.snail.ext.mq.activemq;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@AutoConfigureAfter(value= {JmsAutoConfiguration.class,ActiveMQAutoConfiguration.class})
@PropertySource("classpath:config.properties")
public class AutoActiveMQConfigurer {

}

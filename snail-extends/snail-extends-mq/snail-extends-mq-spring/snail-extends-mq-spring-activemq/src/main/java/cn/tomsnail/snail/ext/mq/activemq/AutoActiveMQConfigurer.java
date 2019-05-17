package cn.tomsnail.snail.ext.mq.activemq;

import cn.tomsnail.snail.core.starter.spring.MixPropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@AutoConfigureAfter(value= {JmsAutoConfiguration.class,ActiveMQAutoConfiguration.class})
@PropertySource(value = {"classpath:config.properties","classpath:config.yml"},factory = MixPropertySourceFactory.class)
public class AutoActiveMQConfigurer {

}

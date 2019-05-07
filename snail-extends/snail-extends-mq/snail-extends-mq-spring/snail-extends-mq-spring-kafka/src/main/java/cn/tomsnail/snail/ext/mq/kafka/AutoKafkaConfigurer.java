package cn.tomsnail.snail.ext.mq.kafka;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@AutoConfigureAfter(value= {KafkaAutoConfiguration.class})
@PropertySource("classpath:config.properties")
public class AutoKafkaConfigurer {

}

package cn.tomsnail.snail.ext.mq.core.spring.consumer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.config.client.plugin.AnnotationConverter;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.ext.mq.core.IJmsReceiveCall;
import cn.tomsnail.snail.ext.mq.core.JmsType;
import cn.tomsnail.snail.ext.mq.core.MQConfig;
import cn.tomsnail.snail.ext.mq.core.JmsConsumerFactory;
import cn.tomsnail.snail.ext.mq.core.kafka.KafkaMQConfig;
import cn.tomsnail.snail.ext.mq.core.spring.annotation.JmsConsumer;

/**
 *        jms消费者初始化
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月18日 下午1:20:02
 * @see 
 */
@Component
public class JmsConsumerSpringInitBean implements InitializingBean{
	
	private JmsConsumerFactory jmsConsumerFactory = new JmsConsumerFactory();	
	
	@Resource
	private List<IJmsReceiveCall> jmsReceiveCalls;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(jmsReceiveCalls==null){
			return;
		}
		for(IJmsReceiveCall jmsReceiveCall:jmsReceiveCalls){
			Class clazz = jmsReceiveCall.getClass();
			if(clazz.isAnnotationPresent(JmsConsumer.class)){
				JmsConsumer jmsConsumer = (JmsConsumer) clazz.getAnnotation(JmsConsumer.class);
				MQConfig mqConfig = null;
				if(jmsConsumer.type().equals(JmsType.ACTIVEMQ)){
					//mqConfig = null;
				}
				if(jmsConsumer.type().equals(JmsType.KAFKA)){
					mqConfig = new KafkaMQConfig();
				}
				
				if(jmsConsumer.type().equals(JmsType.REDIS)){
					if(StringUtils.isNotBlank(jmsConsumer.configClass())){
						mqConfig = (MQConfig) Class.forName(jmsConsumer.configClass()).newInstance();
					}
				}
				
				if(mqConfig==null){
					continue;
				}
				
				mqConfig.setName(jmsConsumer.name());
				mqConfig.setJmsType(jmsConsumer.type());
				mqConfig.setPassword(AnnotationConverter.getValue(jmsConsumer.password()));
				mqConfig.setTopic(false);
				mqConfig.setThreadNum(jmsConsumer.threadNum());
				mqConfig.setGroup(jmsConsumer.group());
				mqConfig.setUrl(AnnotationConverter.getValue(jmsConsumer.url()));
				mqConfig.setUsername(AnnotationConverter.getValue(jmsConsumer.username()));
				mqConfig.setFactoryClass(jmsConsumer.factoryClass());
				jmsConsumerFactory.getConsumerClient(mqConfig).register(mqConfig, jmsReceiveCall);
			}
		}
		
	}

	
	
}

package cn.tomsnail.jms.spring.consumer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import cn.tomsnail.config.client.plugin.AnnotationConverter;
import cn.tomsnail.jms.IJmsReceiveCall;
import cn.tomsnail.jms.JmsType;
import cn.tomsnail.jms.MQConfig;
import cn.tomsnail.jms.core.JmsConsumerFactory;
import cn.tomsnail.jms.kafka.KafkaMQConfig;
import cn.tomsnail.jms.spring.annotation.JmsConsumer;
import cn.tomsnail.util.string.StringUtils;

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

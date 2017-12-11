package cn.tomsnail.jms.spring.sender;



import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import cn.tomsnail.config.client.plugin.AnnotationConverter;
import cn.tomsnail.jms.IJmsSender;
import cn.tomsnail.jms.MQConfig;
import cn.tomsnail.jms.core.JmsSenderFactory;
import cn.tomsnail.jms.spring.annotation.JmsClassd;
import cn.tomsnail.jms.spring.annotation.JmsSender;



/**
 *        发送者初始化
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月18日 下午1:20:02
 * @see 
 */
@Component
public class JmsSenderSpringBeanPostProcessor implements BeanPostProcessor{

	private JmsSenderFactory jmsSenderFactory = new JmsSenderFactory();
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		Class clazz = bean.getClass();
		if(clazz.isAnnotationPresent(JmsClassd.class)){
			Field[] fs = clazz.getDeclaredFields();
			for(Field f:fs){
				if(f.getType().getCanonicalName().startsWith("cn.tomsnail.jms.IJmsSender")&&f.isAnnotationPresent(JmsSender.class)){
					JmsSender jmsSender = f.getAnnotation(JmsSender.class);
					MQConfig mqConfig = new MQConfig();
					mqConfig.setJmsType(jmsSender.type());
					mqConfig.setName(jmsSender.name());
					mqConfig.setPassword(AnnotationConverter.getValue(jmsSender.password()));
					mqConfig.setUrl(AnnotationConverter.getValue(jmsSender.url()));
					mqConfig.setUsername(AnnotationConverter.getValue(jmsSender.username()));
					IJmsSender iJmsSender = jmsSenderFactory.getJmsSender(mqConfig);
					f.setAccessible(true);
					try {
						f.set(bean, iJmsSender);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public JmsSenderFactory getJmsSenderFactory() {
		return jmsSenderFactory;
	}

	public void setJmsSenderFactory(JmsSenderFactory jmsSenderFactory) {
		this.jmsSenderFactory = jmsSenderFactory;
	}

	
}

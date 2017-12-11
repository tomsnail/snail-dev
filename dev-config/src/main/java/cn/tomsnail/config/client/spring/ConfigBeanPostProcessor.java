package cn.tomsnail.config.client.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import cn.tomsnail.config.client.AnnotationConfigListener;
import cn.tomsnail.config.client.annotation.ConfigListener;

/**
 *        spring bean加载时对ConfigListener注解的类进行配置监听
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月12日 下午3:04:04
 * @see 
 */
@Component
public class ConfigBeanPostProcessor implements BeanPostProcessor {
	
	private AnnotationConfigListener annotationConfigListener = new AnnotationConfigListener();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if(bean.getClass().isAnnotationPresent(ConfigListener.class)){
			annotationConfigListener.setClassName(bean.getClass().getCanonicalName());
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}

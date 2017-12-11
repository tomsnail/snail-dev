package cn.tomsnail.hot.load.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;

public class SpringClassLoadBean {

	public static <T> void load(ApplicationContext applicationContext,Class<T> clazz,String beanName){
		BeanDefinition bean = new GenericBeanDefinition();
        //类的全路径
		bean.setBeanClassName(clazz.getName());
		DefaultListableBeanFactory fty = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
		//注册Bean
		fty.registerBeanDefinition(beanName, bean);
	}
	
}

package cn.tomsnail.snail.ext.security.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *        spring上下文操作
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月9日 上午10:15:41
 * @see 
 */
public class SpringContextUtil implements ApplicationContextAware{

	 private static ApplicationContext applicationContext; 
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		SpringContextUtil.applicationContext = arg0;
	}
	
	 public static Object getBean(String name) throws BeansException { 
         return applicationContext.getBean(name); 
	 } 
	 
	 public static boolean isSingleton(String name) 
             throws NoSuchBeanDefinitionException { 
		 return applicationContext.isSingleton(name); 
	 } 
	 
	 public static boolean containsBean(String name) { 
         return applicationContext.containsBean(name); 
	 }
	 
	 public static Object getBean(String name, Class requiredType) throws BeansException { 
		 return applicationContext.getBean(name, requiredType); 
	 } 
	 
	 public static Class getType(String name) throws NoSuchBeanDefinitionException { 
		 return applicationContext.getType(name); 
	 } 

}

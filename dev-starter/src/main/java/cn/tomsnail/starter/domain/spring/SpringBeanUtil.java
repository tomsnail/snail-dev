package cn.tomsnail.starter.domain.spring;

import org.springframework.context.ApplicationContext;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年4月26日 上午11:11:09
	* @see 
	*/     
public class SpringBeanUtil {

	private static ApplicationContext context;
	
	public static void init(ApplicationContext context){
		SpringBeanUtil.context = context;
	}
	
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}
	
	public static <T> Object getBean(Class<T> clazz){
		return context.getBean(clazz);
	}
	
	public static <T> T getClassBean(Class<T> clazz){
		return context.getBean(clazz);
	}
	
}

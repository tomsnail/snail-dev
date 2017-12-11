package cn.tomsnail.starter.domain;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tomsnail.starter.domain.spring.SpringBeanUtil;

/**
 * 主启动类
 * 
 * @author yangsong
 * @date 2015年7月6日 下午5:44:45
 */
public class AppMain {
	
	public static volatile String AppName = cn.tomsnail.host.AppName.AppName;

	/**
	 * 
	 * @author yangsong
	 * @time 2015年7月6日 下午5:44:39
	 * @param
	 * @return
	 */
	public static void main(String[] args) {
		String applicationContextXml = "applicationContext.xml";
		if(args==null||args.length==0){
		}else{
			applicationContextXml = args[0]; 
		}
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:"+applicationContextXml);
		context.start();
		SpringBeanUtil.init(context);
		try {
			ServiceStartupInsertor startupInsertor = context.getBean(ServiceStartupInsertor.class);
			startupInsertor.insertor();
		} catch (NoSuchBeanDefinitionException e) {

		}
		Thread appService = new AppService(context);
		appService.start();
		System.out.println("main started...");
	}

}

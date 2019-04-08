package cn.tomsnail.starter.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tomsnail.starter.domain.spring.SpringBeanUtil;
import cn.tomsnail.util.configfile.ConfigHelp;

/**
 * 主启动类
 * 
 * @author yangsong
 * @date 2015年7月6日 下午5:44:45
 */
public class AppMain {
	
	public static volatile String AppName = cn.tomsnail.host.AppName.AppName;
	
	private static final String SPRING_CONTEXT_XML = ConfigHelp.getInstance("config").getLocalConfig("framework.spring.xml", "");

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
		
		
		if(StringUtils.isNotBlank(SPRING_CONTEXT_XML)) {
			applicationContextXml = SPRING_CONTEXT_XML;
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

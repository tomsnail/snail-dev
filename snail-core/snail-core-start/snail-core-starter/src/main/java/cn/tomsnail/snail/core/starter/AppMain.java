package cn.tomsnail.snail.core.starter;

import cn.tomsnail.snail.core.starter.spring.MixPropertySourceFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tomsnail.snail.core.starter.spring.SpringBeanUtil;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;


/**
 * 主启动类
 * 
 * @author yangsong
 * @date 2015年7月6日 下午5:44:45
 */
@SpringBootApplication
@ImportResource(locations= {"classpath:applicationContext.xml"})
@PropertySource(value = {"classpath:config.properties","classpath:config.yml"},factory = MixPropertySourceFactory.class)
public class AppMain {
	
	public static volatile String AppName = cn.tomsnail.snail.core.util.host.AppName.AppName;
	
	private static final String SPRING_CONTEXT_XML = ConfigHelp.getInstance("config").getLocalConfig("server.spring.xml", "");
	
	private static final String APP_BOOT_TYPE = ConfigHelp.getInstance("config").getLocalConfig("server.boot", "dubbo");

	/**
	 * 
	 * @author yangsong
	 * @time 2015年7月6日 下午5:44:39
	 * @param
	 * @return
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = null;
		if(APP_BOOT_TYPE.equalsIgnoreCase("springboot")) {
			context = SpringApplication.run(AppMain.class, args);
			SpringBeanUtil.init(context);
		}else {
			String applicationContextXml = "applicationContext.xml";
			if(args==null||args.length==0){
			}else{
				applicationContextXml = args[0]; 
			}
			
			
			if(StringUtils.isNotBlank(SPRING_CONTEXT_XML)) {
				applicationContextXml = SPRING_CONTEXT_XML;
			}		
			 context = new ClassPathXmlApplicationContext("classpath:"+applicationContextXml);
			context.start();
			SpringBeanUtil.init(context);
			
			
		}
		
		try {
			ServiceStartupInsertor startupInsertor = context.getBean(ServiceStartupInsertor.class);
			startupInsertor.insertor();
		} catch (NoSuchBeanDefinitionException e) {

		}
		Thread appService = new AppService(context);
		appService.start();
		System.out.println(APP_BOOT_TYPE+" main started...");
		
	}

}

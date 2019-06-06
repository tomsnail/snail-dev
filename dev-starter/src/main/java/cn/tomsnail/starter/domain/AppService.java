package cn.tomsnail.starter.domain;



import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import cn.tomsnail.host.HostIP;
import cn.tomsnail.host.MapIP;
import cn.tomsnail.http.client.OkhttpRequest;
import cn.tomsnail.util.configfile.ConfigHelp;
import cn.tomsnail.util.string.StringUtils;


/**
 *        app启动服务管理
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:52:44
 * @see 
 */
public class AppService extends Thread {
	
	private static final Logger logger = LoggerFactory.getLogger(AppService.class);
	
	private static final String APP_BOOT_REG = ConfigHelp.getInstance("config").getLocalConfig("framework.register.enable", "false");
	
	private static final String APP_BOOT_DOCKER = ConfigHelp.getInstance("config").getLocalConfig("framework.docker.enable", "false");
	
	private static final String REG_HTTP = ConfigHelp.getInstance("config").getLocalConfig("framework.register.http", "http://192.168.169.146:9999");
	private static final String REG_NAME = ConfigHelp.getInstance("config").getLocalConfig("framework.register.name", "");
	
	private ApplicationContext applicationContext;
	
	private long sleepTime = 60000l;
	
	public AppService(ApplicationContext applicationContext){
		this.applicationContext = applicationContext;
	}
	
	public AppService(){
		this.setDaemon(true);
	}

	@Override
	public void run(){
		if(applicationContext!=null){
			try {
				SpringServiceManager manager =  applicationContext.getBean(SpringServiceManager.class);
				if(manager!=null&&manager.isRun()){
					manager.initService();
					manager.startService();
				}
			} catch(NoSuchBeanDefinitionException e){
				System.err.println(e.getMessage());
			}catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		while(true){
			
			
			try {
				if(Boolean.parseBoolean(APP_BOOT_REG)) {
					
					String serviceName = StringUtils.isNotBlank(REG_NAME)?REG_NAME:AppMain.AppName.split("-")[0];
					
					String ipAddress = "";
					
					if(Boolean.parseBoolean(APP_BOOT_DOCKER)) {
						ipAddress = "http://"+MapIP.getIP()+":"+MapIP.getPort();
					}else {
						ipAddress = "http://"+HostIP.HOST_IP+":"+ConfigHelp.getInstance("config").getLocalConfig("framework.register.port", "8666");
					}
					
					Map<String,String> paramMap = new HashMap<>();
					paramMap.put("serviceName", serviceName);
					paramMap.put("ipAddress", ipAddress);
					OkhttpRequest.post(REG_HTTP+"/api/service/register", paramMap);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.currentThread().sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.debug("i am running");
		}
		
	}
	
}

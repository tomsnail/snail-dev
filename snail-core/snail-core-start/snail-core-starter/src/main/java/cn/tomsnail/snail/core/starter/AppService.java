package cn.tomsnail.snail.core.starter;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;


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

	
	private ApplicationContext applicationContext;
	
	private long sleepTime = 30000l;
	
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
				Thread.currentThread().sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.debug("i am running");
		}
		
	}
	
}

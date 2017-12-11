package cn.tomsnail.starter.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 *        默认spring服务管理
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月30日 上午11:01:48
 * @see 
 */
@Configuration
@EnableAsync
@Component
public class SpringServiceManager implements ISpringService{
	
	private static final Logger logger = LoggerFactory.getLogger(SpringServiceManager.class);

	
	private boolean isRun = true;

	
	private List<ISpringService> services;

	@Override
	public void initService() {
		if(services!=null){
			for(ISpringService service:services){
				service.initService();
				logger.info(service.getClass().getName()+" is inited !");
			}
		}
	}

	@Override
	@Async
	public void startService() {		
		if(services!=null){
			for(ISpringService service:services){
				service.startService();
				logger.info(service.getClass().getName()+" is started !");
			}
		}
	}

	@Override
	public void stopService() {
		if(services!=null){
			for(ISpringService service:services){
				service.stopService();
			}
		}
	}

	public List<ISpringService> getServices() {
		return services;
	}

	public void setServices(List<ISpringService> services) {
		this.services = services;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}
	
	public void addServices(ISpringService service) {
		if(this.services==null){
			this.services = new ArrayList<ISpringService>();
		}
		this.services.add(service);
	}

}

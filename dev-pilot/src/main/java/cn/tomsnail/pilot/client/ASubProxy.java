package cn.tomsnail.pilot.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.pilot.model.CommandType;
import cn.tomsnail.pilot.model.ServiceProcess;
import cn.tomsnail.pilot.model.ServiceStatus;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 下午5:38:20
 * @see 
 */
public abstract class ASubProxy implements ISubProxy,IServiceManager{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ASubProxy.class);

	protected int clientType = 0;
	
	protected String name;
	
	protected String alias;
	
	protected String ignorList = "";
	
	public int startProxy(int clientType,String name){
		this.clientType = clientType;
		this.name = name;
		return init();
	}
	
	@Override
	public void restart() {
		stop();
		start();
	}
	
	protected int manageService(ServiceProcess serviceProcess,boolean isProbe){
		if(serviceProcess==null) return -1;
		LOGGER.info("manageService "+serviceProcess.getName());
		if(serviceProcess.getStatus().equals(ServiceStatus.DEBUG)) return 0;
		if(this.ignorList.contains(serviceProcess.getName())) return 0;
		if(CommandType.STOP.equals(serviceProcess.getCommand())){
			LOGGER.info("manageService STOP"+serviceProcess.getName());

			ExeBashUtil.shutdown(serviceProcess.getStopFile());
			return 1;
		}
		if(CommandType.START.equals(serviceProcess.getCommand())){
			LOGGER.info("manageService START isProbe"+serviceProcess.getName()+" "+serviceProcess.getStatus());
			if(isProbe||(!(ServiceStatus.ACTIVE.equals(serviceProcess.getStatus())||ServiceStatus.SANDBY.equals(serviceProcess.getStatus())))){
				if(!isProbe||!ExeBashUtil.check(serviceProcess.getAlias())){
					LOGGER.info("manageService isProbe"+serviceProcess.getName()+" "+serviceProcess.getAlias());
					ExeBashUtil.startup(serviceProcess.getStartFile());
					return 2;
				}
				return 0;
			}
		}
		if(CommandType.RESTART.equals(serviceProcess.getCommand())){
			LOGGER.info("manageService RESTART"+serviceProcess.getName());
			ExeBashUtil.shutdown(serviceProcess.getStopFile());
			ExeBashUtil.startup(serviceProcess.getStartFile());
			return 3;
		}
		return 0;
	}

}

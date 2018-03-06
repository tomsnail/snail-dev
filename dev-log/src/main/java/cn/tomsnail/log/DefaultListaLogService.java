package cn.tomsnail.log;


import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cn.tomsnail.log.annotation.LogTarget;

/**
 *        默认记录服务列表
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 上午9:31:58
 * @see 
 */
@Component("DefaultListaLogService")
public class DefaultListaLogService implements LogService,Runnable{
	
	private static final LinkedBlockingQueue<Log> LOGS = new LinkedBlockingQueue<>(100000);
	
	/**
	 * 活动标识
	 */
	private boolean flag = true;

	@Resource(name="fileLogService")
	private  LogService fileLogService;
	
	@Resource(name="sysoLogService")
	private  LogService sysoLogService;
	
	@Resource(name="centerLogService")
	private LogService centerLogService;
	
	@Autowired(required=false)
	@Qualifier("catService")
	private LogService catService;
	
	@Autowired(required=false)
	@Qualifier("logService")
	private List<LogService> logServices;
	
	public DefaultListaLogService(){
		new Thread(this).start();
	}
	
	@Override
	public void log(Log log) {
		try {
			LOGS.put(log);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public LogService getFileLogService() {
		return fileLogService;
	}

	public void setFileLogService(LogService fileLogService) {
		this.fileLogService = fileLogService;
	}

	public LogService getSysoLogService() {
		return sysoLogService;
	}

	public void setSysoLogService(LogService sysoLogService) {
		this.sysoLogService = sysoLogService;
	}

	public LogService getCenterLogService() {
		return centerLogService;
	}

	public void setCenterLogService(LogService centerLogService) {
		this.centerLogService = centerLogService;
	}

	@Override
	public void run() {
		while(flag){
			try {
				Log log = null;
				log = LOGS.take();
				if(log==null){
					continue;
				}
				if(log.isShared()){
					try {
						if(fileLogService!=null)fileLogService.log(log);
					} catch (Exception e) {
					}
					try {
						if(sysoLogService!=null)sysoLogService.log(log);
					} catch (Exception e) {
					}
					try {
						if(centerLogService!=null)centerLogService.log(log);
					} catch (Exception e) {
					}
					continue;
				}
				if(log.getTarget().equals(LogTarget.LOG)){
					if(fileLogService!=null)fileLogService.log(log);
				}
				if(log.getTarget().equals(LogTarget.SYSOUT)){
					if(sysoLogService!=null)sysoLogService.log(log);
				}
				if(log.getTarget().equals(LogTarget.CENTER)){
					if(centerLogService!=null)centerLogService.log(log);
				}
				if(log.getTarget().equals(LogTarget.CAT)){
					if(fileLogService!=null)fileLogService.log(log);
					if(catService!=null)catService.log(log);
				}
			} catch (Exception e) {
			}
		}
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<LogService> getLogServices() {
		return logServices;
	}

	public void setLogServices(List<LogService> logServices) {
		this.logServices = logServices;
	}
}

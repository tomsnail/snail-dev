package cn.tomsnail.snail.core.log;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.log.annotation.LogLevel;
import cn.tomsnail.snail.core.log.annotation.LogTarget;



/**
 *        日志帮助类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:46:50
 * @see 
 */
@Component
public class LoggerHelper  implements ILogger{
	
	private static final Logger LOGGER = LoggerFactory.getLogger("LoggerHelper");
	
	@Resource(name="DefaultListaLogService")
	private LogService logService; 
	
	public  void info(String info,String target){
		if(!isLoggerService(info,LogLevel.INFO)){
			return;
		}
		Log log = new Log();
		log.addContent(info);
		log.setLevel(Integer.parseInt(LogLevel.INFO));
		log.setTarget(target.toString());
		logService.log(log);
	}
	
	public  void error(String error,Throwable e,String target){
		if(!isLoggerService(error,LogLevel.ERROR)){
			return;
		}
		Log log = new Log();
		log.addContent(error);
		log.setLevel(Integer.parseInt(LogLevel.ERROR));
		log.setDesc(e.getMessage());
		log.setTarget(target.toString());
		logService.log(log);
	}
	
	public  void error(String error,String target){
		if(!isLoggerService(error,LogLevel.ERROR)){
			return;
		}
		Log log = new Log();
		log.addContent(error);
		log.setLevel(Integer.parseInt(LogLevel.ERROR));
		log.setTarget(target.toString());
		logService.log(log);
	}
	
	public  void debug(String debug,String target){
		if(!isLoggerService(debug,LogLevel.DEBUG)){
			return;
		}
		Log log = new Log();
		log.addContent(debug);
		log.setLevel(Integer.parseInt(LogLevel.DEBUG));
		log.setTarget(target.toString());
		logService.log(log);
	}
	
	public  void warn(String warn,String target){
		if(!isLoggerService(warn,LogLevel.DEBUG)){
			return;
		}
		Log log = new Log();
		log.addContent(warn);
		log.setLevel(Integer.parseInt(LogLevel.DEBUG));
		log.setTarget(target.toString());
		logService.log(log);
	}

	
	public void info(String info) {
		this.info(info,null);
	}

	
	public void error(String error, Throwable e) {
		this.error(error,e,null);
	}

	
	public void error(String error) {
		//this.error(error,LogTarget.LOG);
	}

	
	public void debug(String debug) {
		this.debug(debug,null);
	}

	
	public void warn(String warn) {
		this.warn(warn,null);
	}

	private boolean isLoggerService(String loginfo,String level){
		if(logService!=null){
			switch(level){
				case LogLevel.DEBUG:LOGGER.debug(loginfo);break;
				case LogLevel.ERROR:LOGGER.error(loginfo);break;
				case LogLevel.INFO:
				default:LOGGER.info(loginfo);break;
			}
			return true;
		}
		return false;
	}
}

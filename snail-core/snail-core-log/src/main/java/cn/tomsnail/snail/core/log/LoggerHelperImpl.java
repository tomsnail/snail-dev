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
@Component("LoggerHelper")
public class LoggerHelperImpl implements LoggerHelper{
	
	private static final Logger LOGGER = LoggerFactory.getLogger("LoggerHelperImpl");
	
	@Resource(name="DefaultListaLogService")
	private LogService logService; 
	
	public  void info(String info,LogTarget target){
		if(!isLoggerService(info,LogLevel.INFO)){
			return;
		}
		Log log = new Log();
		log.addContent(info);
		log.setLevel(Integer.valueOf(LogLevel.INFO));
		log.setTarget(target.toString());
		logService.log(log);
	}
	
	public  void error(String error,Throwable e,LogTarget target){
		if(!isLoggerService(error,LogLevel.ERROR)){
			return;
		}
		Log log = new Log();
		log.addContent(error);
		log.setLevel(Integer.valueOf(LogLevel.ERROR));
		log.setDesc(e.getMessage());
		log.setTarget(target.toString());
		logService.log(log);
	}
	
	public  void error(String error,LogTarget target){
		if(!isLoggerService(error,LogLevel.ERROR)){
			return;
		}
		Log log = new Log();
		log.addContent(error);
		log.setLevel(Integer.valueOf(LogLevel.ERROR));
		log.setTarget(target.toString());
		logService.log(log);
	}
	
	public  void debug(String debug,LogTarget target){
		if(!isLoggerService(debug,LogLevel.DEBUG)){
			return;
		}
		Log log = new Log();
		log.addContent(debug);
		log.setLevel(Integer.valueOf(LogLevel.DEBUG));
		log.setTarget(target.toString());
		logService.log(log);
	}
	
	public  void warn(String warn,LogTarget target){
		if(!isLoggerService(warn,LogLevel.DEBUG)){
			return;
		}
		Log log = new Log();
		log.addContent(warn);
		log.setLevel(Integer.valueOf(LogLevel.DEBUG));
		log.setTarget(target.toString());
		logService.log(log);
	}
	
	private boolean isLoggerService(String loginfo,String level){
		if(logService!=null){
			switch(level){
				case LogLevel.DEBUG:LOGGER.debug(loginfo);break;
				case LogLevel.INFO:LOGGER.info(loginfo);break;
				case LogLevel.ERROR:LOGGER.error(loginfo);break;
			}
			return true;
		}
		return false;
	}
}

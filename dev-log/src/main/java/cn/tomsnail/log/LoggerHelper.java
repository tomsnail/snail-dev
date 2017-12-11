package cn.tomsnail.log;

import cn.tomsnail.log.annotation.LogTarget;


/**
 *        日志帮助类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:46:50
 * @see 
 */
public interface LoggerHelper {
	
	public  void info(String info,LogTarget target);
	
	public  void error(String error,Throwable e,LogTarget target);
	
	public  void error(String error,LogTarget target);
	
	public  void debug(String debug,LogTarget target);
	
	public  void warn(String warn,LogTarget target);
	
}

package cn.tomsnail.snail.core.log;

import cn.tomsnail.snail.core.log.annotation.LogTarget;

/**
 *        日志帮助类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:46:50
 * @see 
 */
public interface ILogger {
	
	public  void info(String info,String target);
	
	public  void error(String error,Throwable e,String target);
	
	public  void error(String error,String target);
	
	public  void debug(String debug,String target);
	
	public  void warn(String warn,String target);

	public  void info(String info);

	public  void error(String error,Throwable e);

	public  void error(String error);

	public  void debug(String debug);

	public  void warn(String warn);
	
}

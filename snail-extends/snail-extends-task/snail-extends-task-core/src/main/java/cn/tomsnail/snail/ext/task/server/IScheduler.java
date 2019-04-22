package cn.tomsnail.snail.ext.task.server;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 下午4:17:36
 * @see 
 */
public interface IScheduler extends Runnable {

	public void schedule();
	
	public void start();
	
	public void init();
	
}

package cn.tomsnail.pilot.client;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 下午5:42:13
 * @see 
 */
public interface IServiceManager extends ISubProxyNode{
	
	public int init();

	public void start();
	
	public void stop();
	
	public void restart();
	
}

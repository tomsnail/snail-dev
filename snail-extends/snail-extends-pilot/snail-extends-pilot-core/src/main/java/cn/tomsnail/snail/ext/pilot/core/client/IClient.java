package cn.tomsnail.snail.ext.pilot.core.client;

import cn.tomsnail.snail.ext.pilot.core.model.ServiceProcess;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 上午9:47:26
 * @see 
 */
public interface IClient {

	public void init();
	
	public void register(Object info);
	
	public void send(Object info);
	
	public void start();
	
	public void stop();
	
	public void destory();
	
	public ServiceProcess getClientInfo();
	
}

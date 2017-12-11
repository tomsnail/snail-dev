package cn.tomsnail.pilot.server;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月6日 上午11:22:48
 * @see 
 */
public interface IServer extends IServices{

	public void init();
	
	public void start();
	
	public void stop();
	
	public void notifly(String path,int type);
	
	public void addCallback(ICallback callback);
	
}

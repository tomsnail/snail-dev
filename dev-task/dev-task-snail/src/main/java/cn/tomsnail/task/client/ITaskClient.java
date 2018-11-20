package cn.tomsnail.task.client;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 上午11:39:27
 * @see 
 */
public interface ITaskClient {

	
	public void register();
	
	public void callback(Object data);
	
	public void init() throws Exception;
	
	public void destory();
	
	public void cancel();
	
}

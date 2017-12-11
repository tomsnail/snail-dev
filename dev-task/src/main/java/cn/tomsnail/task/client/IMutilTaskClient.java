package cn.tomsnail.task.client;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 上午11:39:27
 * @see 
 */
public interface IMutilTaskClient {

	public <T> void register(String name,TaskCall<T> taskCall,int type,long time);
	
	public void callback(Object data);
	
	public void init();
	
	public void destory(String name);
	
	public void cancel(String name);
	
	public void call(Object data);
	
}

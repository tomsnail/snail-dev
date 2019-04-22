package cn.tomsnail.snail.ext.task.client;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月25日 下午4:15:00
 * @see 
 */
public interface TaskCallScheduler {

	public <T> void scheduler(String name,TaskCall<T> taskCall,int type,long time);
	
}

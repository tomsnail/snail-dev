package cn.tomsnail.snail.ext.task.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 上午11:38:00
 * @see 
 */
public abstract class MutilTaskCall implements IMutilTaskClient{
	
	protected static final Map<String,TaskCall> TaskCallMap = new ConcurrentHashMap<String,TaskCall>();

	public <T> void addTaskCall(String name,TaskCall<T> taskCall){
		if(!TaskCallMap.containsKey(name)){
			TaskCallMap.put(name, taskCall);
		}
	}
	
	public <T>void removeTaskCall(String name){
		TaskCall<T> taskCall = TaskCallMap.remove(name);
		if(taskCall!=null){
			taskCall = null;
		}
	}
	
	public MutilTaskCall() {
		
	}
	
	
	
}

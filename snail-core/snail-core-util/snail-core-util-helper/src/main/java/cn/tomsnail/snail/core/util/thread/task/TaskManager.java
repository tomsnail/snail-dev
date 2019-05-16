package cn.tomsnail.snail.core.util.thread.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

public class TaskManager {
	
	static HashMap<String,SchedulerModel> taskMap = new HashMap<String, SchedulerModel>();

	static HashMap<String,Timer> timerMap = new HashMap<String, Timer>();
	
	private static void start(SchedulerModel sm){
		sm.status = 1;
		Timer timer = new Timer();
		timer.schedule(sm, 0,Long.parseLong(sm.getTaskModel().getPeriod())*1000L);
		timerMap.put(sm.getTaskModel().getUuid(), timer);
	}
	private static void stop(SchedulerModel sm){
		Timer timer = timerMap.get(sm.getTaskModel().getUuid());
		timer.cancel();
	}
	static void update(){
		Iterator<Map.Entry<String,SchedulerModel>> it = taskMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,SchedulerModel> entry = it.next();
			SchedulerModel sm = entry.getValue();
			if(sm.status==0){
				start(sm);
			}
		}
	}
	
	static void addTask(SchedulerModel model){
		String uuid = model.getTaskModel().getUuid();
		if(taskMap.containsKey(uuid)){
			SchedulerModel old = taskMap.get(uuid);
			if(!old.getTaskModel().equals(model.getTaskModel())){
				stop(taskMap.remove(uuid));
				taskMap.put(uuid, model);
			}
		}else{
			taskMap.put(uuid, model);
		}
	}
}

package cn.tomsnail.snail.core.util.thread.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class TimerManager {

	private static List<TimerEvent> tasks = new ArrayList<TimerEvent>();
	
	private static List<Timer> timers = new ArrayList<Timer>();


	/**
	 * @return the tasks
	 */
	public List<TimerEvent> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<TimerEvent> tasks) {
		TimerManager.tasks = tasks;
	}
	/**
	 * 启动定时任务
	 * @author yangsong
	 * @date 2012-7-10 下午9:39:27
	 */
	public synchronized void start(){
		for(TimerEvent te:tasks){
			Timer timer = new Timer();
			try{
				timer.schedule(te, te.getDelay(), te.getPeriod()*1000L);
				te.setRund(true);
				timers.add(timer);
			}catch(RuntimeException e){
				e.printStackTrace();
				te.setRund(false);
			}
			
		}
	}
	/**
	 * 关闭定时任务
	 * @author yangsong
	 * @date 2012-7-10 下午9:39:42
	 */
	public synchronized void close(){
		for(Timer timer:timers){
			timer.cancel();
		}
		for(TimerEvent te:tasks){
			te.setRund(false);
		}
	}
	
	
}

package cn.tomsnail.util.thread.task;

public class TaskScheduler {
	
	TaskScheduler(){
		
	}
	
	private SchedulerModel schedulerModel;

	public void scheduler(SchedulerModel schedulerModel){
		this.schedulerModel = schedulerModel;
		scheduler();
	}
	
	private void scheduler(){
		TaskManager.addTask(schedulerModel);
		TaskManager.update();
	}
	
}

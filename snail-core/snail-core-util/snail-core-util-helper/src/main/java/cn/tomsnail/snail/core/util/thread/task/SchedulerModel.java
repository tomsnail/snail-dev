package cn.tomsnail.snail.core.util.thread.task;

import java.util.TimerTask;

public abstract class SchedulerModel extends TimerTask{
	
	int status = 0;

	private TaskModel taskModel;
	
	/**
	 * @return the taskModel
	 */
	public TaskModel getTaskModel() {
		return taskModel;
	}

	/**
	 * @param taskModel the taskModel to set
	 */
	public void setTaskModel(TaskModel taskModel) {
		this.taskModel = taskModel;
	}


	
}

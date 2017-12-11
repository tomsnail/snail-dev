package cn.tomsnail.util.thread.task;

public class TaskModel {

	private String uuid;
	
	private String taskName;
	
	private String period;

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(obj instanceof TaskModel){
			TaskModel tm = (TaskModel) obj;
			if(tm.getPeriod()!=null&&period!=null){
				if(!tm.getPeriod().equals(period)){
					return false;
				}
			}
//			if(tm.getTaskName()!=null&&taskName!=null){
//				if(!tm.getTaskName().equals(taskName)){
//					return false;
//				}
//			}
			if(tm.getUuid()!=null&&uuid!=null){
				if(!tm.getUuid().equals(uuid)){
					return false;
				}
			}
		}
		return true;
	}
	
	
}

package cn.tomsnail.snail.ext.task.client;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 上午11:38:00
 * @see 
 */
public abstract class ATaskCall<T> implements TaskCall<T>,ITaskClient{

	protected String taskName;
	
	protected String time;
	
	protected String clientTime;
	
	//1 定时调度，只调度一次，time是下次调度时间格式 2016-06-28 14:12:12  2 定时调度，只调度一次 time是下次调度的间隔   单位毫秒  3 周期调度，time是每次调度的间隔 单位毫秒
	protected int type;
	//0 初始化状态 等待执行 1 执行中  2 执行完成  3 取消 -1 异常
	protected int status = 0;
	
	protected TaskCall<T> taskCall;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

	public String getClientTime() {
		return clientTime;
	}

	public void setClientTime(String clientTime) {
		this.clientTime = clientTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public TaskCall<T> getTaskCall() {
		return taskCall;
	}

	public void setTaskCall(TaskCall<T> taskCall) {
		this.taskCall = taskCall;
	}

	public ATaskCall(String taskName,String time, int type,int status) throws Exception {
		this();
		this.taskName = taskName;
		this.time = time;
		this.status = status;
		this.type = type;
	}
	
	public ATaskCall() throws Exception {
		
	}
	
	public String getTaskInfo(){
		return this.getType()+","+this.getTaskName()+","+System.currentTimeMillis()+","+this.getTime()+","+this.status;
	}
	
}

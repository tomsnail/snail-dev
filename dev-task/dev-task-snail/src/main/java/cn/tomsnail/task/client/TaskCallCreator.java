package cn.tomsnail.task.client;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月25日 下午1:44:00
 * @see 
 */
public class TaskCallCreator implements TaskCallScheduler{
	
	public static final int ONCE = 1;
	
	public static final int CYCLE = 3;
	
	private static final TaskCallCreator CREATOR = new TaskCallCreator();
	
	private MutilTaskCall mutilTaskCall = new MutilZookeeperTaskCall();
	
	private TaskCallCreator(MutilTaskCall mutilTaskCall){
		this.mutilTaskCall = mutilTaskCall;
	}
	
	private TaskCallCreator(){
	}
	
	public static TaskCallCreator creator(MutilTaskCall mutilTaskCall){
		return new TaskCallCreator(mutilTaskCall);
	}
	
	public static TaskCallCreator creator(){
		return CREATOR;
	}

	public <T> void scheduler(String name,TaskCall<T> taskCall,int type,long time){
		if(name==null){
			name = taskCall.getClass().getCanonicalName()+""+System.currentTimeMillis();
		}
		mutilTaskCall.register(name, taskCall, type, time);
	}
	
	public static void main(String[] args) {
		TaskCallCreator.creator().scheduler(null,new TaskCall<String>() {
			@Override
			public String call() {
				System.out.println("call");
				return null;
			}
		}, TaskCallCreator.ONCE, 5000l);
	}
	
}

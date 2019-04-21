package cn.tomsnail.snail.core.util.thread.task;

public class Test {

	public static void main(String[] args) {
		TaskModel tm = new TaskModel();
		tm.setUuid("1");
		tm.setTaskName("taks1");
		tm.setPeriod("5");
		SchedulerModel sm = new Sch();
		sm.setTaskModel(tm);
		TaskScheduler ts = TaskschedulerFactory.getTaskScheduler();
		ts.scheduler(sm);
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TaskModel tm1 = new TaskModel();
		tm1.setUuid("2");
		tm1.setTaskName("taks2");
		tm1.setPeriod("5");
		SchedulerModel sm1 = new Sch();
		sm1.setTaskModel(tm1);
		TaskScheduler ts1 = TaskschedulerFactory.getTaskScheduler();
		ts1.scheduler(sm1);
	}
}
class Sch extends SchedulerModel{

	@Override
	public void run() {
		System.out.println(this.getTaskModel().getTaskName()+":"+System.currentTimeMillis());
		
	}
	
}

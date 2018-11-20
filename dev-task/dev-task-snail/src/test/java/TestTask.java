import java.util.Date;

import org.junit.Test;

import cn.tomsnail.task.client.ExampleTaskCall;
import cn.tomsnail.task.client.TaskCall;
import cn.tomsnail.task.client.TaskCallCreator;
import cn.tomsnail.task.server.ZkTimeSlotScheduler;



public class TestTask {
	//type-----1 定时调度，只调度一次，time是下次调度时间格式 2016-06-28 14:12:12  2 定时调度，只调度一次 time是下次调度的间隔   单位毫秒  3 周期调度，time是每次调度的间隔 单位毫秒
	//status---0 初始化状态 等待执行 1 执行中  2 执行完成  3 取消 -1 异常
	@Test
	public void testServer(){
		//启动服务端
		ZkTimeSlotScheduler zkTimeSlotScheduler = new ZkTimeSlotScheduler();
		zkTimeSlotScheduler.init();
		zkTimeSlotScheduler.start();
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClient1(){
		TaskCallCreator.creator().scheduler(null,new TaskCall<String>() {
			@Override
			public String call() {
				System.out.println(new Date());
				return "ok";
			}
		}, TaskCallCreator.CYCLE, 5000l);
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClient2() throws Exception{
		System.out.println("testclient--begin--");
		
		for (int i = 0; i < 3; i++) {
			String taskname1="timeJobOnce"+i;
			ExampleTaskCall exampleTaskCall = new ExampleTaskCall();
			exampleTaskCall.setTaskName(taskname1);
			exampleTaskCall.setTime("2016-08-19 17:30:00");
			exampleTaskCall.setType(1);
			exampleTaskCall.register();
			
			ExampleTaskCall exampleTaskCall2 = new ExampleTaskCall();
			String taskname2="timeJobMore"+i;
			exampleTaskCall2.setTaskName(taskname2);
			exampleTaskCall2.setTime(60000+"");
			exampleTaskCall2.setType(2);
			exampleTaskCall2.register();
			
			ExampleTaskCall exampleTaskCall4 = new ExampleTaskCall();
			String taskname3="cycleJobMore"+i;
			exampleTaskCall4.setTaskName(taskname3);
			exampleTaskCall4.setTime(900000+"");
			exampleTaskCall4.setType(3);
			exampleTaskCall4.register();
			
		}
		
		
		System.out.println("testclient--end--");
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

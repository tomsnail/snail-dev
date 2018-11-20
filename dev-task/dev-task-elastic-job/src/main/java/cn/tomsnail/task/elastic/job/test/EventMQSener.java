package cn.tomsnail.task.elastic.job.test;

import com.dangdang.ddframe.job.event.mq.JobEventMQSener;
import com.dangdang.ddframe.job.event.type.JobExecutionEvent;
import com.dangdang.ddframe.job.event.type.JobStatusTraceEvent;

public class EventMQSener implements JobEventMQSener{

	@Override
	public void send(JobExecutionEvent jobExecutionEvent) {
		System.out.println(jobExecutionEvent);
		
	}

	@Override
	public void send(JobStatusTraceEvent jobStatusTraceEvent) {
		System.out.println(jobStatusTraceEvent);
		
	}

}

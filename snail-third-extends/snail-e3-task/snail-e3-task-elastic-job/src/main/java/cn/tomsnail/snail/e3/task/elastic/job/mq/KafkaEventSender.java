package cn.tomsnail.snail.e3.task.elastic.job.mq;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;

import com.dangdang.ddframe.job.event.mq.JobEventMQSener;
import com.dangdang.ddframe.job.event.type.JobExecutionEvent;
import com.dangdang.ddframe.job.event.type.JobStatusTraceEvent;

import cn.tomsnail.snail.core.util.JsonUtil;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;


public class KafkaEventSender implements JobEventMQSener{
	
	
	private String QUEUE_NAME = ConfigHelp.getInstance("config").getLocalConfig("system.elastic.job.event-mq-kafka-queue", "Ealstic_JOB");
	
	
    private KafkaTemplate kafkaTemplate;
    

	public KafkaEventSender(KafkaTemplate kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void send(JobExecutionEvent jobExecutionEvent) {
		ListenableFuture future = kafkaTemplate.send(QUEUE_NAME, JsonUtil.toJson(jobExecutionEvent));
        future.addCallback(o -> System.out.println("send-消息发送成功："+jobExecutionEvent.getJobName()), throwable -> System.out.println("消息发送失败："+jobExecutionEvent.getJobName()));
	}

	@Override
	public void send(JobStatusTraceEvent jobStatusTraceEvent) {
		ListenableFuture future = kafkaTemplate.send(QUEUE_NAME, JsonUtil.toJson(jobStatusTraceEvent));
        future.addCallback(o -> System.out.println("send-消息发送成功："+jobStatusTraceEvent.getJobName()), throwable -> System.out.println("消息发送失败："+jobStatusTraceEvent.getJobName()));
	}

}

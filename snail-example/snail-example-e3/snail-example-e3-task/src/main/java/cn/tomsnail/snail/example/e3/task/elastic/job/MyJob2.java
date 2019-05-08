package cn.tomsnail.snail.example.e3.task.elastic.job;

import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ExecutionContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import cn.tomsnail.snail.e3.task.elastic.job.JobType;
import cn.tomsnail.snail.e3.task.elastic.job.SpringElasticJob;

@Component
@SpringElasticJob(jobName="TestJob", type=JobType.single,cron="${test.cron}")
public class MyJob2 implements SimpleJob{

	@Override
	public void execute(ExecutionContext executionContext) {
		System.out.println("MyJob");
	}

}

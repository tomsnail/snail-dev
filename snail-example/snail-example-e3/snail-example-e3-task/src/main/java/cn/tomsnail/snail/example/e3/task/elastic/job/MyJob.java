package cn.tomsnail.snail.example.e3.task.elastic.job;

import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ExecutionContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import cn.tomsnail.snail.e3.task.elastic.job.JobType;
import cn.tomsnail.snail.e3.task.elastic.job.SpringElasticJob;


@SpringElasticJob(jobName="TestJob", type=JobType.multiple,jobDataSource="mysqlDataSource",jobSQL="select JOB_NAME,CRON,PARAMS from forward_task t where del_flag = '0' ")
public class MyJob implements SimpleJob{

	@Override
	public void execute(ExecutionContext executionContext) {
		System.out.println("MyJob");
	}

}

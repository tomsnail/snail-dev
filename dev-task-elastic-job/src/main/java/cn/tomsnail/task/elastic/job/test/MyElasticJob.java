package cn.tomsnail.task.elastic.job.test;


import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MyElasticJob implements SimpleJob{
	

	@Override
	public void execute(ShardingContext context) {
		System.out.println(context.getShardingItem());
		System.out.println(context.getJobName());
		System.out.println(context.getTaskId());
	}

}

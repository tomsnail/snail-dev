package cn.tomsnail.task.elastic.job.test;


import org.springframework.stereotype.Service;

import com.dangdang.ddframe.job.api.ExecutionContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

@Service("MyElasticJob")
public class MyElasticJob implements SimpleJob{
	

	@Override
	public void execute(ExecutionContext context) {
		System.out.println(context.getShardingContext().getShardingItem());
		System.out.println(context.getShardingContext().getJobName());
		System.out.println(context.getShardingContext().getTaskId());
		context.addMessage("execute now:"+System.currentTimeMillis());
		if(System.currentTimeMillis()%2==1){
			int i = 1/0;
		}
	}

}

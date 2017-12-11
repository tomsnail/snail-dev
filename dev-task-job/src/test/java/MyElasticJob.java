import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MyElasticJob implements SimpleJob {

	@Override
	public void execute(ShardingContext context) {
		switch (context.getShardingItem()) {
			case 0:break;
			case 1:break;
			default:break;
		}
	}
}

package cn.tomsnail.snail.ext.task.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ExampleTaskCall extends ZookeeperTaskCall<String> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExampleTaskCall.class);

	public ExampleTaskCall() throws Exception {
		super();
	}

	@Override
	public String call() {
		LOGGER.info("[任务中心客户端]客户端任务执行"+this.taskName);
		return "ok";
	}
}

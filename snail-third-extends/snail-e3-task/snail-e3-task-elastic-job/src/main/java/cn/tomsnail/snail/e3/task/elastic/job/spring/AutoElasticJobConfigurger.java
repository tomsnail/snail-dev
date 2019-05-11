package cn.tomsnail.snail.e3.task.elastic.job.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;

@Configuration
@PropertySource("config.properties")
public class AutoElasticJobConfigurger {
	
	
	
	@Bean
	public CoordinatorRegistryCenter coordinatorRegistryCenter() {
		String serverLists = ConfigHelp.getInstance("config").getLocalConfig("system.elastic.job.server-lists", "127.0.0.1:2181");
		String namespace = ConfigHelp.getInstance("config").getLocalConfig("system.elastic.job.namespace", "SnailJob");//base-sleep-time-milliseconds max-sleep-time-milliseconds max-retries
		int baseSleepTimeMilliseconds = ConfigHelp.getInstance("config").getLocalConfig("system.elastic.job.base-sleep-time-milliseconds", 1000);
		int maxSleepTimeMilliseconds = ConfigHelp.getInstance("config").getLocalConfig("system.elastic.job.max-sleep-time-milliseconds", 3000);
		int maxRetries = ConfigHelp.getInstance("config").getLocalConfig("system.elastic.job.max-retries", 3);
		ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverLists, namespace);
		zookeeperConfiguration.setBaseSleepTimeMilliseconds(baseSleepTimeMilliseconds);
		zookeeperConfiguration.setMaxRetries(maxRetries);
		zookeeperConfiguration.setMaxSleepTimeMilliseconds(maxSleepTimeMilliseconds);
		ZookeeperRegistryCenter zookeeperRegistryCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
		zookeeperRegistryCenter.init();
		return zookeeperRegistryCenter;
	}
	

}

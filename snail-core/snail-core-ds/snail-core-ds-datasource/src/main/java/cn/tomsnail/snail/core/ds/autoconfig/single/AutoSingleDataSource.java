package cn.tomsnail.snail.core.ds.autoconfig.single;


import cn.tomsnail.snail.core.starter.spring.MixPropertySourceFactory;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.SQLException;

@Configuration
@PropertySource(value = {"classpath:config.properties","classpath:config.yml"},factory = MixPropertySourceFactory.class)
public class AutoSingleDataSource {
	

	@Bean(name="dataSource",initMethod="init",destroyMethod="close")
	public DruidDataSource init() {
		String r = ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.name", "");
		if(StringUtils.isBlank(r)){
			return null;
		}
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.url", ""));
		dataSource.setDriverClassName(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.driver-class-name", "com.mysql.jdbc.Driver"));
		dataSource.setUsername(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.username", ""));
		dataSource.setPassword(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.password", ""));
		dataSource.setInitialSize(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.initial-size", "1")));
		dataSource.setMinIdle(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.min-idle", "1")));
		dataSource.setMaxActive(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.max-active", "1")));
		dataSource.setMaxWait(Long.parseLong(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.max-wait", "60000")));
		dataSource.setTimeBetweenConnectErrorMillis(Long.parseLong(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.time-between-eviction-runs-millis", "60000")));
		dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.min-evictable-idle-time-millis", "300000")));
		dataSource.setValidationQuery(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.validation-query", ""));
		dataSource.setTestWhileIdle(Boolean.parseBoolean(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.test-while-idle", "true")));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.test-on-borrow", "false")));
		dataSource.setTestOnReturn(Boolean.parseBoolean(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.test-on-return", "false")));
		
		dataSource.setPoolPreparedStatements(Boolean.parseBoolean(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.pool-prepared-statements", "false")));
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.max-pool-prepared-statement-per-connection-size", "20")));
		try {
			dataSource.setFilters(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.filters", "stat"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return dataSource;
	}



}

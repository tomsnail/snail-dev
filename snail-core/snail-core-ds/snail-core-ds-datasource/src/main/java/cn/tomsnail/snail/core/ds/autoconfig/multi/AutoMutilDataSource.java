package cn.tomsnail.snail.core.ds.autoconfig.multi;


import cn.tomsnail.snail.core.ds.router.RountingDataSource;
import cn.tomsnail.snail.core.starter.spring.MixPropertySourceFactory;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = {"classpath:config.properties","classpath:config.yml"},factory = MixPropertySourceFactory.class)
@ComponentScan(basePackages=" cn.tomsnail.snail.core.ds.router")
public class AutoMutilDataSource {


	@Bean(name="dataSource")
	public RountingDataSource initRountingDataSource() throws SQLException {
		String r = ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.name.router", "");
		if(StringUtils.isBlank(r)){
			return null;
		}

		String routerNameStr = ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.name.router-name", "");
		if(StringUtils.isBlank(routerNameStr)){
			return null;
		}

		String routerType = ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.name.router-type", "");
		if(StringUtils.isBlank(routerType)){
			return null;
		}

		String routerDefault = ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.name.router-default", "");
		if(StringUtils.isBlank(routerDefault)){
			return null;
		}

		RountingDataSource rountingDataSource = new RountingDataSource();
		rountingDataSource.setRouteType(routerType);

		Map<Object, Object> dataSourceMap = new HashMap<>();

		String[] routerNames = routerNameStr.split(",");
		for(String routerName:routerNames){
			String rn = "."+routerName;
			DruidDataSource dataSource = new DruidDataSource();
			dataSource.setUrl(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.url"+rn, ""));
			dataSource.setDriverClassName(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.driver-class-name"+rn, "com.mysql.jdbc.Driver"));
			dataSource.setUsername(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.username"+rn, ""));
			dataSource.setPassword(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.password"+rn, ""));
			dataSource.setInitialSize(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.initial-size"+rn, "1")));
			dataSource.setMinIdle(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.min-idle"+rn, "1")));
			dataSource.setMaxActive(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.max-active"+rn, "1")));
			dataSource.setMaxWait(Long.parseLong(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.max-wait"+rn, "60000")));
			dataSource.setTimeBetweenConnectErrorMillis(Long.parseLong(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.time-between-eviction-runs-millis"+rn, "60000")));
			dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.min-evictable-idle-time-millis"+rn, "300000")));
			dataSource.setValidationQuery(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.validation-query"+rn, ""));
			dataSource.setTestWhileIdle(Boolean.parseBoolean(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.test-while-idle"+rn, "true")));
			dataSource.setTestOnBorrow(Boolean.parseBoolean(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.test-on-borrow"+rn, "false")));
			dataSource.setTestOnReturn(Boolean.parseBoolean(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.test-on-return"+rn, "false")));

			dataSource.setPoolPreparedStatements(Boolean.parseBoolean(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.pool-prepared-statements"+rn, "false")));
			dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.max-pool-prepared-statement-per-connection-size"+rn, "20")));
			try {
				dataSource.setFilters(ConfigHelp.getInstance("config").getLocalConfig("spring.datasource.druid.filters"+rn, "stat"));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			dataSource.init();
			dataSourceMap.put(routerName,dataSource);
		}
		rountingDataSource.setTargetDataSources(dataSourceMap);
		rountingDataSource.setDefaultTargetDataSource(dataSourceMap.get(routerDefault));
		return rountingDataSource;
	}

}

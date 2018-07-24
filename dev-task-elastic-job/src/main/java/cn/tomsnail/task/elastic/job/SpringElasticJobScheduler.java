package cn.tomsnail.task.elastic.job;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;

@Component
public class SpringElasticJobScheduler implements ApplicationListener<ContextRefreshedEvent>{
	
	private static final Logger logger = LoggerFactory.getLogger(SpringElasticJobScheduler.class);
	
	static final List<SpringElasticJobM> ELASTIC_JOBS = new ArrayList<SpringElasticJobM>();
	
	private ApplicationContext applicationContext;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		applicationContext = event.getApplicationContext();
		
		if(applicationContext==null){
			return;
		}
		
		if(CollectionUtils.isEmpty(ELASTIC_JOBS)){
			return;
		}
		
		for(SpringElasticJobM springElasticJobM:ELASTIC_JOBS){
			try {
				createSpringElasticJob(springElasticJobM);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}
	
	private void createSpringElasticJob(SpringElasticJobM springElasticJobM){
		
		if(springElasticJobM==null){
			throw new NullPointerException("springElasticJobM is null");
		}
		
		ElasticJob elasticJob = springElasticJobM.getElasticJob();
		
		if(elasticJob==null){
			throw new NullPointerException("elasticJob is null");
		}
		
		CoordinatorRegistryCenter registryCenter = getCoordinatorRegistryCenter(springElasticJobM);
		
		if(registryCenter==null){
			throw new NullPointerException("registryCenter is null");
		}
		
		LiteJobConfiguration liteJobConfiguration = getLiteJobConfiguration(springElasticJobM);
		
		if(liteJobConfiguration==null){
			throw new NullPointerException("liteJobConfiguration is null");
		}
		
		SpringJobScheduler jobScheduler = new SpringJobScheduler(elasticJob, registryCenter, liteJobConfiguration);
		jobScheduler.init();
		
	
	}
	
	private CoordinatorRegistryCenter getCoordinatorRegistryCenter(SpringElasticJobM springElasticJobM){
		if(springElasticJobM==null||StringUtils.isBlank(springElasticJobM.getRegistryCenter())){
			return null;
		}				
		return applicationContext.getBean(springElasticJobM.getRegistryCenter(), CoordinatorRegistryCenter.class);
	}
	
	private LiteJobConfiguration getLiteJobConfiguration(SpringElasticJobM springElasticJobM){
		
		if(springElasticJobM==null||springElasticJobM.getElasticJob()==null||StringUtils.isBlank( springElasticJobM.getJobName())){
			return null;
		}
		
		String _jobName = springElasticJobM.getJobName();
		
		String _className = springElasticJobM.getElasticJob().getClass().getCanonicalName();
		
		String _cron = springElasticJobM.getCron();
		
		String sdi = springElasticJobM.getShardingTotalCount();
		
		int shardingTotalCount = 1;
		
		try {
			shardingTotalCount = Integer.parseInt(sdi);
		} catch (NumberFormatException e) {
			logger.error("", e);
		}
		
		if(StringUtils.isNotBlank(_cron)){
			return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(_jobName, _cron, shardingTotalCount).build(), _className)).overwrite(springElasticJobM.overwrite).build();
		}
		String _jobDataSource = springElasticJobM.getJobDataSource();
		
		String _jobSQL = springElasticJobM.getJobSQL();
		
		if(StringUtils.isNoneBlank(_jobDataSource,_jobSQL)){
			
			try {
				_cron =  getCronSQL(_jobDataSource,_jobSQL);
			} catch (SQLException e) {
				logger.error("", e);
			}
			if(StringUtils.isNotBlank(_cron)){
				return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(_jobName, _cron, shardingTotalCount).build(), _className)).overwrite(springElasticJobM.overwrite).build();
			}
		}
		
		String _dao = springElasticJobM.getDao();
		
		String _daoMethod = springElasticJobM.getDaoMethod(); 
		
		if(StringUtils.isNoneBlank(_dao,_daoMethod)){
			
			try {
				_cron =  getCronDao(_dao,_daoMethod);
			} catch (Exception e) {
				logger.error("", e);
			}
			if(StringUtils.isNotBlank(_cron)){
				return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(_jobName, _cron, shardingTotalCount).build(), _className)).overwrite(springElasticJobM.overwrite).build();
			}
		}
		
		return null;
	}
	
	private String getCronSQL(String dataSourceStr,String sql) throws SQLException{
		if(StringUtils.isAnyBlank(sql,dataSourceStr)){
			return null;
		}
		DataSource dataSource = applicationContext.getBean(dataSourceStr, DataSource.class);
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		String cron = null;
		while(rs.next()){    
			cron = rs.getString("cron") ;    
	     }    
		return cron;
	}
	
	private String getCronDao(String dao,String daoMethod) throws Exception {
		if(StringUtils.isAnyBlank(dao,daoMethod)){
			return null;
		}
		Object d = applicationContext.getBean(dao);
		Method m = d.getClass().getMethod(daoMethod);		
		Object r = m.invoke(d);
		if(r!=null){
			return r.toString();
		}
		
		return null;
	}

}

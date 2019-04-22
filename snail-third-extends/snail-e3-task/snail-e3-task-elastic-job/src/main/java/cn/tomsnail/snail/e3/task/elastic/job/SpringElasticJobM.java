package cn.tomsnail.snail.e3.task.elastic.job;

import com.dangdang.ddframe.job.api.ElasticJob;

public class SpringElasticJobM {

	String jobName;
	
	String cron;
	
	String registryCenter;
	
	String shardingTotalCount;
	
	boolean overwrite;
	
	String eventTraceRdbDataSource;
	
	String jobDataSource;
	
	String jobSQL;
	
	String dao;
	
	String daoMethod;
	
	JobType jobType;
	
	ElasticJob elasticJob;
	
	String params;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getRegistryCenter() {
		return registryCenter;
	}

	public void setRegistryCenter(String registryCenter) {
		this.registryCenter = registryCenter;
	}

	public String getShardingTotalCount() {
		return shardingTotalCount;
	}

	public void setShardingTotalCount(String shardingTotalCount) {
		this.shardingTotalCount = shardingTotalCount;
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	public String getEventTraceRdbDataSource() {
		return eventTraceRdbDataSource;
	}

	public void setEventTraceRdbDataSource(String eventTraceRdbDataSource) {
		this.eventTraceRdbDataSource = eventTraceRdbDataSource;
	}

	public String getJobDataSource() {
		return jobDataSource;
	}

	public void setJobDataSource(String jobDataSource) {
		this.jobDataSource = jobDataSource;
	}

	public String getJobSQL() {
		return jobSQL;
	}

	public void setJobSQL(String jobSQL) {
		this.jobSQL = jobSQL;
	}

	public String getDao() {
		return dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

	public String getDaoMethod() {
		return daoMethod;
	}

	public void setDaoMethod(String daoMethod) {
		this.daoMethod = daoMethod;
	}
	
	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public ElasticJob getElasticJob() {
		return elasticJob;
	}

	public void setElasticJob(ElasticJob elasticJob) {
		this.elasticJob = elasticJob;
	}
	
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public SpringElasticJobM(){
		
	} 
	
	public SpringElasticJobM(SpringElasticJob springElasticJob,ElasticJob elasticJob){
		this.cron = springElasticJob.cron();
		this.dao = springElasticJob.dao();
		this.daoMethod = springElasticJob.daoMethod();
		this.eventTraceRdbDataSource = springElasticJob.eventTraceRdbDataSource();
		this.jobDataSource = springElasticJob.jobDataSource();
		this.jobName = springElasticJob.jobName();
		this.jobSQL = springElasticJob.jobSQL();
		this.overwrite = springElasticJob.overwrite();
		this.registryCenter = springElasticJob.registryCenter();
		this.shardingTotalCount = springElasticJob.shardingTotalCount();
		this.elasticJob = elasticJob;
		this.jobType = springElasticJob.type();
		this.params = springElasticJob.params();
	} 
	
	public  SpringElasticJobM (String jobName,String cron,String params,ElasticJob elasticJob){
		this.jobName = jobName;
		this.cron = cron;
		this.params = params;
		this.elasticJob = elasticJob;
		this.overwrite = true;
	}
	
}

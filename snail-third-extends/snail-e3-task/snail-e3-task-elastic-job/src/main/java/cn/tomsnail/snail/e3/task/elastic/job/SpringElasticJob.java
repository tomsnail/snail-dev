package cn.tomsnail.snail.e3.task.elastic.job;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target({ElementType.TYPE})
public @interface SpringElasticJob {

	String jobName();
	
	String cron() default "";
	
	String registryCenter() default  "";
	
	String shardingTotalCount() default "1";
	
	boolean overwrite() default true;
	
	String eventTraceRdbDataSource() default "";
	
	String jobDataSource() default "";
	
	String jobSQL() default "";
	
	String dao() default "";
	
	String daoMethod() default "";
	
	JobType type() default JobType.single; 
	
	String params() default "";
	
	
}

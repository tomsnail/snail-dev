package cn.tomsnail.snail.e3.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *        redis使用注解
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:48:52
 * @see 
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisAConfig {

	String url();
	
	String password() default "";
	
	int maxIdle() default 100;
	
	int maxWaitMillis() default 1000;
	
	int maxTotal() default 100;
	
	boolean testOnBorrow() default true;
	
	int port() default 6379;
	
}

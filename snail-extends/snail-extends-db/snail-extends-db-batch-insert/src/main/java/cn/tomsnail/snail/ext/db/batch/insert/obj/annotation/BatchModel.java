package cn.tomsnail.snail.ext.db.batch.insert.obj.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月20日 下午2:45:41
	* @see 
	*/     
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BatchModel {
	
	public String tablename() default "";

	public boolean isBatch() default false;
	
	public boolean isSmart() default false;
	
	public int coreSize() default 1;
	
	public int maxSize() default 2;
	
	public int dbSize() default 1;
	
}

package cn.tomsnail.framwork.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanValidator {

	  
	   /**
		*        是否bean中所有属性都验证一遍后才抛出异常
		*/
	boolean isAllValidator() default false;
	
}

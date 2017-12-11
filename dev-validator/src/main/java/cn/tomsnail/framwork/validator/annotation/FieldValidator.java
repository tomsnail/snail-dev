package cn.tomsnail.framwork.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.tomsnail.framwork.validator.RuleType;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldValidator {

	   /**
		*        规则集合
		*/
	RuleType[] rules() default {};
	
	  /**
		*        验证类型
		*/
	String[] validTypes() default {};
	
	   /**
		*       规则所对应的值集合
		*/
	String[] values() default {};
	   /**
		*        map中key名
		*/
	String mapperName() default "";
	   /**
		*        是否只是转换为数据，不进行验证
		*/
	boolean onlyToBean() default false;
	   /**
		*        如果是date类型时的时间字符串格式
		*/
	String dateFormat() default "yyyy-MM-dd HH:mm:ss";
	   /**
		*        验证失败后，抛出的自定义错误信息
		*/
	String[] errorMsg() default {""};
	
}

package cn.tomsnail.http.register.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 *        httprest spring服务端注解
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:40:10
 * @see 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@ComponentScan("cn.tomsnail.http.register.springmvc")
public @interface HttpRestServiceSpring {
	
	/**
	 *        逻辑聚合组
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:39:21
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String group() default "default";
	
}

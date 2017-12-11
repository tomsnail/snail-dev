package cn.tomsnail.jms.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.jms.JmsType;

/**
 *        jms消费注解
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月18日 上午10:20:05
 * @see 
 */
  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年9月27日 下午2:35:07
	* @see 
	*/     
    
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@ComponentScan(basePackages="cn.tomsnail.jms.spring.consumer")
public @interface JmsConsumer {

	/**
	 *        消息队列名称
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:29:00
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String name();
	
	/**
	 *        队列类型
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:29:09
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	JmsType type() default JmsType.REDIS;
	
	/**
	 *        url
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:29:17
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String url();
	
	/**
	 *        用户名
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:29:22
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String username() default "";
	
	/**
	 *        密码
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:29:31
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String password() default "";
	
	/**
	 *        所属组，应用于kafka
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:29:36
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String group() default "default";
	
	/**
	 *        线程数量
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:29:52
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	int threadNum() default 2;
	
	  
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月27日 下午2:35:01
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	String factoryClass() default "";
	
	
	  
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月27日 下午2:35:11
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	String configClass() default "";
	

}

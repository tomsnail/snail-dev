package cn.tomsnail.http.register.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 *        httprest dubbo服务端注解
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:39:34
 * @see 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpRestServiceDubbo{
	
	/**
	 *        dubbo服务类，如果实现类不以Impl结尾时，该值就是实现类的全路径
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:39:50
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String dubboService() default "";
	
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
	
	 /**        权重，默认100，值越大，得到调用的机会越多
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月28日 下午2:48:36
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	int weight() default 100;
	
}

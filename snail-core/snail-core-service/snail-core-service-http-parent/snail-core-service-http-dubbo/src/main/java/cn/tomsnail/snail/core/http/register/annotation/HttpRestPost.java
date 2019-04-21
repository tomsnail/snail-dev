package cn.tomsnail.snail.core.http.register.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.HttpMethod;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@HttpMethod(HttpMethod.POST)
public @interface HttpRestPost{

	/**
	 *        服务名
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:39:14
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String serviceName();
	
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
	
	/**
	 *        权重，默认100，值越大，得到调用的机会越多
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
	
	/**
	*        
	* @methodauthor yangsong
	* @methodversion 0.0.1
	* @date 2016年12月24日 下午3:04:17
	* @see 
	* @param                   
	* @return               
	* @status 正常
	* @exception no
	*/
	
String version() default "0.0.1";
	
}

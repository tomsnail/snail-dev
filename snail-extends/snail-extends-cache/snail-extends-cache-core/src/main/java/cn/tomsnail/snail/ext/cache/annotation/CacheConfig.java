package cn.tomsnail.snail.ext.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.tomsnail.snail.ext.cache.core.CacheType;




/**
 *        缓存配置注解
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午2:35:15
 * @see 
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheConfig {

	/**
	 *        url链接，可使用配置中心配置${url}
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:35:31
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String url() default "";
	
	/**
	 *  	端口，可使用配置中心配置   ${port}
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:35:56
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String port() default "0";
	
	/**
	 *        缓存类型，默认为本地缓存
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:36:27
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public CacheType cacheType() default CacheType.LOCAL;
	
	/**
	 *        缓存类型，默认为本地缓存
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:36:27
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String cachedType() default "";
	
	/**
	 *        缓存名称
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:36:42
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String name() default "";
	
	/**
	 *        缓存过期时间
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:37:47
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String expire() default "10000";
	
	/**
	 *        缓存超时时间
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:38:37
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String timeout() default "10000";
	
	/**
	 *        缓存的用户名，主要用于redis
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:39:19
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String username() default "";
	
	/**
	 *        缓存的密码，主要用于redis
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:39:37
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String password() default "";
	
}

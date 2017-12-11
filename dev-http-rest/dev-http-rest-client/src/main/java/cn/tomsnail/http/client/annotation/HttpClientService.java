package cn.tomsnail.http.client.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.tomsnail.http.client.cluster.LBType;
import cn.tomsnail.http.client.cluster.fail.FailType;


/**
 *        httprest客户端服务注解
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 下午4:36:37
 * @see 
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpClientService {

	/**
	 *        服务名
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:14:06
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String serviceName();
	
	/**
	 *        地址
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:14:21
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String address() default "";
	
	/**
	 *        是否mock数据
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:14:38
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	boolean mock() default false;
	
	/**
	 *        注册类型
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:14:46
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	RegisterType registerType() default RegisterType.LOCAL;
	
	/**
	 *        消费者
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:15:23
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String customer() default "";
	
	
	/**
	 *        失败策略。默认为重试
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月28日 下午3:02:16
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String failType() default FailType.Retry;
	
	/**
	 *        当失败策略为重试时有效，默认为3次
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月28日 下午3:03:04
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	int retryCount() default 3;
	
	
	/**
	 *        负载策略，默认为轮询
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月28日 下午4:03:06
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	String lbType() default LBType.Polling;
	
	/**
	 *        请求超时时间，默认为30s，单位秒
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月31日 下午2:57:03
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	int timeout() default 30;
	
	
}

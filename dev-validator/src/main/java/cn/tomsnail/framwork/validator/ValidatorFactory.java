package cn.tomsnail.framwork.validator;

import cn.tomsnail.framwork.validator.handler.BizValidator;

/**
 *        简单验证工程,获得数据
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2015年12月24日 下午2:08:01
 * @see 
 */
public class ValidatorFactory{
	
	/**
	 *        String验证器
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年1月12日 上午11:27:33
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static StringValidator getStringValidator(){
		return new StringValidator();
	} 
	/**
	 *        Int验证器
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年1月12日 上午11:27:33
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static IntegerValidator getIntegerValidator(){
		return new IntegerValidator();
	} 
	/**
	 *        Long验证器
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年1月12日 上午11:27:33
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static LongValidator getLongValidator(){
		return new LongValidator();
	} 
	
	public static BeanValidator getBeanValidator(){
		return new BeanValidator();
	}
	
	public static BizValidator getBizValidator(){
		return new BizValidator();
	}
	
}

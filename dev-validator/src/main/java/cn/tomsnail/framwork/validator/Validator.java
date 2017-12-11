package cn.tomsnail.framwork.validator;

import java.util.Map;

import cn.tomsnail.framwork.validator.exception.ParamValidatorException;


/**
 *        验证接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2015年12月23日 下午3:52:54
 * @see 
 */
public interface Validator<T> {

	/**
	 *        通过验证获得值，如果验证不通过抛出异常
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2015年12月23日 下午3:53:07
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception ParamValidatorException
	 */
	public T getValidatorValue(Map<String,Object> valueMap,String key,String errorMsg) throws ParamValidatorException;
	
	
	/**
	 *        通过验证获得值，如果验证不通过抛出异常
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2015年12月23日 下午3:53:07
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception ParamValidatorException
	 */
	public T getValidatorValue(Map<String,Object> valueMap,String key) throws ParamValidatorException;
	
	
	
	/**
	 *        添加规则
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2015年12月23日 下午3:53:42
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception ParamValidatorException
	 */
	public Validator<T>  add(RuleType rule);
	
	/**
	 *        添加规则和验证值
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:06:27
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public Validator<T>  add(RuleType rule,Object value);
	
	/**
	 *  	添加自定义验证规则      
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:06:38
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public Validator<T> add(CustValidator custValidator);
	
}

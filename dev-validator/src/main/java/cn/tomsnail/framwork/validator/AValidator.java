package cn.tomsnail.framwork.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.tomsnail.framwork.validator.exception.ParamValidatorException;



/**
 *        验证抽象类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:04:15
 * @see 
 */
public  abstract class AValidator<T> implements Validator<T>{

	
	/**
	 * 验证规则
	 */
	protected ValidatorRule<T> rule = new ValidatorRule<T>();
	
	/**
	 * 自定义验证列表
	 */
	protected List<CustValidator> custValidators = new ArrayList<CustValidator>();


	
	@Override
	public Validator<T> add(RuleType ruleType) {
		rule.add(ruleType);
		return this;
	}
	
	@Override
	public Validator<T> add(RuleType ruleType,Object value) {
		rule.add(ruleType,value);
		return this;
	}
	
	
	public Validator<T> add(CustValidator custValidator){
		custValidators.add(custValidator);
		return this;
	}
	
	/**
	 *        进行自定义验证
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:05:49
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	protected boolean doCustValidators(Map<String, Object> valueMap){
		if(custValidators==null||custValidators.size()<1){
			return true;
		}
		for(CustValidator custValidator:custValidators){
			if(!custValidator.validator(valueMap)){
				return false;
			}
		}
		return true;
	}
	
	public String getValidFaildMsg(String desc,String t){
		if(StringUtils.isBlank(desc)){
			return t+"";
		}
		return desc;
	}
	
	public T getBeanValidatorValue(String key,T value,Map<RuleType,String > errorMap) throws ParamValidatorException{
		rule.validator(key,value,errorMap);
		return value;
	}
	
	
}

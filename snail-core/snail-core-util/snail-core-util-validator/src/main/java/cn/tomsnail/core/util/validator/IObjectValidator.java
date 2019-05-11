package cn.tomsnail.core.util.validator;

import java.util.Map;

import cn.tomsnail.core.util.validator.exception.ParamValidatorException;


public interface IObjectValidator {
	
	
	public Object getValidBean(Map<String, Object> valueMap,Class clazz) throws ParamValidatorException;
	
	public Object getValidBean(Map<String, Object> valueMap,Class clazz,String validType) throws ParamValidatorException;
	
	public boolean valid(Object obj) throws ParamValidatorException;

}

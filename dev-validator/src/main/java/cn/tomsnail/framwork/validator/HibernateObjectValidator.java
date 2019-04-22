package cn.tomsnail.framwork.validator;


import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.validator.HibernateValidator;

import cn.tomsnail.framwork.validator.exception.ParamValidatorException;

public class HibernateObjectValidator implements IObjectValidator{
	
	private static final BeanValidator BEAN_VALIDATOR = new BeanValidator();
	
	private static Validator fastValidator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();	
	
	public Object getValidBean(Map<String, Object> valueMap,Class clazz) throws ParamValidatorException{
		return this.getValidBean(valueMap, clazz, null);
		
	}
	
	public Object getValidBean(Map<String, Object> valueMap,Class clazz,String validType) throws ParamValidatorException{
		
		if(valueMap==null||clazz==null){
			throw new ParamValidatorException(ValidateUtil.getValidFaildMsg("", "Map值或者Key值空异常"));
		}
		if(!clazz.isAnnotationPresent(cn.tomsnail.framwork.validator.annotation.BeanValidator.class)){
			if(valueMap!=null){
				try {
					Object obj = clazz.newInstance();  
					BeanUtils.populate(obj, valueMap);
					valid(obj);
					return obj;
				} catch (Exception e) {
					throw new ParamValidatorException(e.getMessage());
				} 
				
			}
		}else{
			Object obj = BEAN_VALIDATOR.getValidBean(valueMap, clazz, validType);
			valid(obj);
			return obj;
		}
		
		return null;
	}
	
	
	public boolean valid(Object obj) throws ParamValidatorException{
		if(obj==null){
			throw new ParamValidatorException("对象为空");
		}
		Set<ConstraintViolation<Object>> constraintViolations = fastValidator.validate(obj);
		if (constraintViolations.size() > 0) {
            throw new ParamValidatorException(constraintViolations.iterator().next().getMessage());
        }
		return true;
	}

}

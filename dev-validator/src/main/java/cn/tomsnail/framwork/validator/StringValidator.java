package cn.tomsnail.framwork.validator;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.tomsnail.framwork.validator.exception.ParamValidatorException;
import cn.tomsnail.framwork.validator.security.SQLSecurityManager;
import cn.tomsnail.framwork.validator.security.XssSecurityManager;


/**
 *        String验证
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:08:30
 * @see 
 */
public class StringValidator extends AValidator<String>{

	@Override
	public String getValidatorValue(Map<String, Object> valueMap, String key,String errorMsg)
			throws ParamValidatorException {
			if(valueMap==null||key==null){
				throw new ParamValidatorException(this.getValidFaildMsg(errorMsg, "Map值或者Key值空异常"));
			}
			if(!doCustValidators(valueMap)){
				throw new ParamValidatorException(this.getValidFaildMsg(errorMsg, "custvalidator error"));
			}
			String value = (String) valueMap.get(key);
			
			if(!StringUtils.isBlank(value)){
				if(SQLSecurityManager.sql_inj(value)){
					throw new ParamValidatorException(this.getValidFaildMsg(errorMsg, "SQL ING Error"));
				}
				value = XssSecurityManager.replaceSpecialChars(value);
			}
			
			String v = super.rule.validator(key,value,errorMsg);
			if(v!=null){
				return v;
			}
			return value;
	}
	
	@Override
	public String getValidatorValue(Map<String, Object> valueMap, String key) throws ParamValidatorException {
			return getValidatorValue(valueMap,key,"");
	}



}

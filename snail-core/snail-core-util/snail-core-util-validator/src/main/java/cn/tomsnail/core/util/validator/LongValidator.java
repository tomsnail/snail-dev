package cn.tomsnail.core.util.validator;

import java.util.Map;

import cn.tomsnail.core.util.validator.exception.ParamValidatorException;


/**
 *        Long值验证
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:08:11
 * @see 
 */
public class LongValidator extends AValidator<Long> {

	@Override
	public Long getValidatorValue(Map<String, Object> valueMap, String key,String errorMsg)
			throws ParamValidatorException {
		if(valueMap==null||key==null){
			throw new ParamValidatorException(this.getValidFaildMsg(errorMsg, "Map值或者Key值空异常"));
		}
		if(!doCustValidators(valueMap)){
			throw new ParamValidatorException(this.getValidFaildMsg(errorMsg, "custvalidator error"));
		}
		String value = (String) valueMap.get(key);
		Long v = null;
		try{
			v = Long.valueOf(value);
		}catch(NumberFormatException exception){
			if(value==null||value.equals("")){
			}else{
				throw new ParamValidatorException(this.getValidFaildMsg(errorMsg, key+"的值不是Long类型"+value));
			}
		}
		super.rule.validator(key,v,errorMsg);
		return v;
	}

	@Override
	public Long getValidatorValue(Map<String, Object> valueMap,String key)
			throws ParamValidatorException {
		return this.getValidatorValue(valueMap, key,"");
	}

	

	

	

}

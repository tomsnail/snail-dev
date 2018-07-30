package cn.tomsnail.validator;

import cn.tomsnail.framwork.validator.RuleType;
import cn.tomsnail.framwork.validator.annotation.BeanValidator;
import cn.tomsnail.framwork.validator.annotation.FieldValidator;

@BeanValidator(isAllValidator=true)
public class Class4 {

	@FieldValidator(rules={RuleType.NotNullValue},values={""})
	private String class41;

	public String getClass41() {
		return class41;
	}

	public void setClass41(String class41) {
		this.class41 = class41;
	}
	
	
	
}

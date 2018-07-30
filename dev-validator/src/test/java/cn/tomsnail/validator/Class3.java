package cn.tomsnail.validator;

import cn.tomsnail.framwork.validator.RuleType;
import cn.tomsnail.framwork.validator.annotation.BeanValidator;
import cn.tomsnail.framwork.validator.annotation.FieldValidator;

@BeanValidator(isAllValidator=true)
public class Class3 {

	@FieldValidator(rules={RuleType.NotNull,RuleType.MOBILE},values={"",""})
	private String class31;
	
	@FieldValidator(rules={RuleType.NotNull,RuleType.MOBILE},values={"",""})
	private String class32;
	
	@FieldValidator(rules={RuleType.NotNull},values={""})
	private Class4 class4;

	public String getClass31() {
		return class31;
	}

	public void setClass31(String class31) {
		this.class31 = class31;
	}

	public String getClass32() {
		return class32;
	}

	public void setClass32(String class32) {
		this.class32 = class32;
	}

	public Class4 getClass4() {
		return class4;
	}

	public void setClass4(Class4 class4) {
		this.class4 = class4;
	}
	
	
	
}

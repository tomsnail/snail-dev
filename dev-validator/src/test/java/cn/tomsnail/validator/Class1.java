package cn.tomsnail.validator;

import cn.tomsnail.framwork.validator.RuleType;
import cn.tomsnail.framwork.validator.annotation.BeanValidator;
import cn.tomsnail.framwork.validator.annotation.FieldValidator;

@BeanValidator(isAllValidator=true)
public class Class1 {
	
	@FieldValidator(rules={RuleType.NotNull,RuleType.VALUE},values={"","11"},errorMsg={"","123"})
	private String class11;
	@FieldValidator(rules={RuleType.NotNull},values={""})
	private Class2 class2;
	@FieldValidator(rules={RuleType.NotNull},values={""})
	private Class3 class3;

	public String getClass11() {
		return class11;
	}

	public void setClass11(String class11) {
		this.class11 = class11;
	}

	public Class2 getClass2() {
		return class2;
	}

	public void setClass2(Class2 class2) {
		this.class2 = class2;
	}

	public Class3 getClass3() {
		return class3;
	}

	public void setClass3(Class3 class3) {
		this.class3 = class3;
	}
	
	

}

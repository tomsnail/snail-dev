package cn.tomsnail.validator;

import cn.tomsnail.framwork.validator.RuleType;
import cn.tomsnail.framwork.validator.annotation.BeanValidator;
import cn.tomsnail.framwork.validator.annotation.FieldValidator;

@BeanValidator(isAllValidator=true)
public class Class2 {
	
	@FieldValidator(rules={RuleType.NotNull,RuleType.LIMIT},values={"2"})
	private long class21;
	@FieldValidator()
	private float class22;
	@FieldValidator(rules={RuleType.NotNull},values={""})
	private Class4 class4;

	public long getClass21() {
		return class21;
	}

	public void setClass21(long class21) {
		this.class21 = class21;
	}

	public float getClass22() {
		return class22;
	}

	public void setClass22(float class22) {
		this.class22 = class22;
	}

	public Class4 getClass4() {
		return class4;
	}

	public void setClass4(Class4 class4) {
		this.class4 = class4;
	}
	
	

}

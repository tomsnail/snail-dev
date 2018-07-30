package cn.tomsnail.validator;

import cn.tomsnail.framwork.validator.ValidatorFactory;
import cn.tomsnail.framwork.validator.exception.ParamValidatorException;

public class ValidMian {

	public static void main(String[] args) throws ParamValidatorException {
		Class4 class4 = new Class4();
		
		Class3 class3 = new Class3();
		
		
		Class2 class2 = new Class2();
		
		
		Class1 class1 = new Class1();
		class1.setClass11("11");
		class1.setClass2(class2);
		class1.setClass3(class3);
		
		class2.setClass4(class4);
		
		ValidatorFactory.getBeanValidator().valid(class1);
	}
}

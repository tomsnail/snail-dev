package cn.tomsnail.framwork.validator.handler.test;

import cn.tomsnail.framwork.validator.ValidatorFactory;
import cn.tomsnail.framwork.validator.handler.ValidResult;

public class Main1 {

	public static void main(String[] args) {

		M1 m1 = new M1();
		M2 m2 = new M2();
		
		ValidResult validResult = ValidatorFactory.getBizValidator().add(m2, new Test1()).add(m1, new Test2()).fastFail().doValid();
		
		System.out.println(validResult.getErrorDesc());
		
		
			
	}

}

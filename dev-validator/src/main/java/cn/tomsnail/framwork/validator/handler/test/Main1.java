package cn.tomsnail.framwork.validator.handler.test;

import cn.tomsnail.framwork.validator.ValidatorFactory;
import cn.tomsnail.framwork.validator.handler.ValidResult;

public class Main1 {

	public static void main(String[] args) {

		M1 m1 = new M1();
		M2 m2 = null;
		
		ValidResult validResult = ValidatorFactory.getBizValidator().add(()->{
									if(m2==null){
										return ValidResult.ERROR;
									}
									return ValidResult.OK;
							}).add(m1, new Test2()).fastFail().doValid();
		
		System.out.println(validResult.getErrorDesc());
		
		
			
	}

}

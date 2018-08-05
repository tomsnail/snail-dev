package cn.tomsnail.framwork.validator.handler.test;

import cn.tomsnail.framwork.validator.handler.ValidHandler;
import cn.tomsnail.framwork.validator.handler.ValidResult;

public class Test2 implements ValidHandler<M1>{

	@Override
	public ValidResult validate(M1 t) {
		return ValidResult.ERROR;
	}

}

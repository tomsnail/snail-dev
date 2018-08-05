package cn.tomsnail.framwork.validator.handler.test;

import cn.tomsnail.framwork.validator.handler.ValidHandler;
import cn.tomsnail.framwork.validator.handler.ValidResult;

public class Test1 implements ValidHandler<M2>{

	@Override
	public ValidResult validate(M2 t) {
		return ValidResult.ERROR;
	}

}

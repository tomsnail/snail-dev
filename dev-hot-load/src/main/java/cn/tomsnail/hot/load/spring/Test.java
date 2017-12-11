package cn.tomsnail.hot.load.spring;

import org.springframework.stereotype.Component;

import cn.tomsnail.hot.load.ITest;


@Component
public class Test implements ITest{

	@Override
	public void test() {
		System.out.println("test");
	}

}

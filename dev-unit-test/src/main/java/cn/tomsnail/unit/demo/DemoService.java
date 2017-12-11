package cn.tomsnail.unit.demo;

import java.util.Map;

import cn.tomsnail.unit.test.annotation.AutoTester;

@AutoTester
public interface DemoService {

	public String demo(DemoModel demoModel,String t,int a,Map map);
	
}

package cn.tomsnail.hot.load;

import java.util.List;

import org.junit.Test;

import cn.tomsnail.hot.load.annotation.HotLoad;
import cn.tomsnail.hot.load.annotation.HotLoadObject;
import cn.tomsnail.hot.load.annotation.HotLoadType;
import cn.tomsnail.unit.test.BaseTest;

@HotLoad
public class TestMain extends BaseTest{

	@HotLoadObject
	private ITest test;
	
	@HotLoadObject(type=HotLoadType.list)
	private List<ITest> tests;
	
	@Test
	public void test(){		
		while(true){
			sleep(1000);
			if(test!=null){
				System.out.println("test=================");
				test.test();
			}
			if(tests!=null){
				System.out.println("tests================");
				for(ITest t:tests){
					t.test();
				}
			}
		}
	}
	
}

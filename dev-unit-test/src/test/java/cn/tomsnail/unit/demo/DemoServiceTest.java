/**
 * Copyright &copy; 2016-2017 
 * Author : tomsnail.cn
 */
package cn.tomsnail.unit.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tomsnail.unit.test.BaseTest;

import cn.tomsnail.unit.demo.DemoModel;
import java.util.Map;
import java.util.HashMap;

/**
 * DemoService Test
 * @author Tomsnail.cn
 * @version 2017-09-16
 */
public class DemoServiceTest extends BaseTest {
	
	@Autowired
	private DemoService demoService;
	
	
	@Test
	public void testDemo(){
		demoService.demo( new DemoModel(),"",0, new HashMap());
	}
	
	
	
	
}
package cn.tomsnail.dubbo.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})  
public class TestDubbo {

	@Resource(name="TestServiceClient")
	private TestService testService;
	
	@Test
	public void testDubbo(){
		testService.test();
	}
	
}

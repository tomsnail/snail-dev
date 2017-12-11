package cn.tomsnail.log;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tomsnail.config.client.ConfigClientFactory;
import cn.tomsnail.unit.test.BaseTest;

public class LogTest extends BaseTest {

	
	
	@Test
	public void test(){
		 boolean isLogger = false;
    	 try {
    		 isLogger = Boolean.valueOf(ConfigClientFactory.getInstance().getConfigClient().getConfig("system.islogger","true"));
		 } catch (Exception e) {
			 System.err.println(e.getMessage());
		 }
    	 System.out.println(!isLogger);
	}
}

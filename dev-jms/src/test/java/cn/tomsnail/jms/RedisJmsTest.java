package cn.tomsnail.jms;


import org.springframework.beans.factory.annotation.Autowired;

import cn.tomsnail.jms.test.JmsSenderTest;
import cn.tomsnail.unit.test.BaseTest;

public class RedisJmsTest extends BaseTest{

	@Autowired
	private JmsSenderTest jmsSenderTest;
	
	public void send(){
		
		jmsSenderTest.test();
		sleep();
	}
	
	public void receiver(){
		sleep();
	}
	
}

package cn.tomsnail.jms.test;

import java.util.concurrent.TimeUnit;


import cn.tomsnail.jms.IJmsSender;
import cn.tomsnail.jms.JmsType;
import cn.tomsnail.jms.spring.annotation.JmsClassd;
import cn.tomsnail.jms.spring.annotation.JmsSender;

@JmsClassd
public class JmsSenderTest {

	@JmsSender(name="testmsg",url="192.168.169.156:8457",type=JmsType.KAFKA)
	private IJmsSender iJmsSender;
	
	public void test(){
		try {
			while(true){
				TimeUnit.SECONDS.sleep(1);
				iJmsSender.send("testmsg", "test:"+System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

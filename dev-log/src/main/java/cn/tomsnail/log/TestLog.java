package cn.tomsnail.log;

import org.springframework.stereotype.Service;

import cn.tomsnail.log.annotation.LogLevel;
import cn.tomsnail.log.annotation.LogPoint;
import cn.tomsnail.log.annotation.LogTarget;

@Service
public class TestLog {

	public String log = "123";
	
	@LogPoint(desc="testlogpoint",level=LogLevel.DEBUG,target=LogTarget.SYSOUT)
	public TestLog test(TestLog log) throws Exception{
		System.out.println("test");
		TestLog log2 = new TestLog();
		log2.log = "124";
		return log2;
	}
	
}

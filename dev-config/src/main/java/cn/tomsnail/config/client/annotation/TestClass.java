package cn.tomsnail.config.client.annotation;

import org.springframework.stereotype.Component;


@ConfigListener
@Component
public class TestClass {

	public static String TEST_KEY = "value1";
	
}

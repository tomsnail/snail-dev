package cn.tomsnail.snail.core.config.client.annotation;

import org.springframework.stereotype.Component;


@ConfigListener
@Component
public class TestClass {

	public static String TEST_KEY = "value1";
	
}

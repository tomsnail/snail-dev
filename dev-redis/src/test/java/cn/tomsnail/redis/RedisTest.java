package cn.tomsnail.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tomsnail.unit.test.BaseTest;


public class RedisTest extends BaseTest{

	@Autowired
	private RedisDemo redisDemo;
	
	@Test
	public void test(){
		redisDemo.demo();
	}
	
}

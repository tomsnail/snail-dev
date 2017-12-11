package cn.tomsnail.cache.test;

import javax.annotation.Resource;

import org.junit.Test;

import cn.tomsnail.unit.test.BaseTest;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 下午5:36:21
 * @see 
 */
public class CacheTest extends BaseTest{
	
	@Resource
	private CacheDemo cacheDemo;

	@Test
	public void test(){
		cacheDemo.demo();
	}
	
}

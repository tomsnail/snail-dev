package cn.tomsnail.cache.test;


import cn.tomsnail.cache.annotation.CacheClassd;
import cn.tomsnail.cache.annotation.CacheConfig;
import cn.tomsnail.cache.core.CacheType;
import cn.tomsnail.cache.core.ICache;

@CacheClassd
public class CacheDemo {

	@CacheConfig(cacheType=CacheType.EHCACHE,url="./ehcache.xml")
	private ICache cache;
	
	public void demo(){
		cache.set("test", "test1", 9000l);
		System.out.println(cache.get("test"));
		try {
			Thread.currentThread().sleep(10000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(cache.get("test"));
	}
	
}

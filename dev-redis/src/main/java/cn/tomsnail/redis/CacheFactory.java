package cn.tomsnail.redis;

import cn.tomsnail.cache.core.CacheType;
import cn.tomsnail.cache.core.DefaultCacheFactory;

public class CacheFactory extends DefaultCacheFactory{
	
	public void init(){
		super.init();
		map.put(CacheType.REDIS, new RedisMapCacheFactory());
	}

}

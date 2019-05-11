package cn.tomsnail.snail.e3.j2cache;

import cn.tomsnail.snail.ext.cache.core.CacheType;
import cn.tomsnail.snail.ext.cache.core.DefaultCacheFactory;

public class CacheFactory extends DefaultCacheFactory{

	public void init(){
		super.init();
		map.put(CacheType.J2CACHE, new J2CacheMapCacheFactory());
	}
	
}

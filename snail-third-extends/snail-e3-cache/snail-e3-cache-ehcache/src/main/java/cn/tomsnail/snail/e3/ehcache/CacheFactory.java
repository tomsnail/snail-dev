package cn.tomsnail.snail.e3.ehcache;

import cn.tomsnail.snail.ext.cache.core.CacheType;
import cn.tomsnail.snail.ext.cache.core.DefaultCacheFactory;

public class CacheFactory extends DefaultCacheFactory{
	
	public void init(){
		super.init();
		map.put(CacheType.LOCAL, new EhcacheMapCacheFactory());
	}

}

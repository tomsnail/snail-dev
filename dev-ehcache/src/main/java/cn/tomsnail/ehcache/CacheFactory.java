package cn.tomsnail.ehcache;

import cn.tomsnail.cache.core.CacheType;
import cn.tomsnail.cache.core.DefaultCacheFactory;

public class CacheFactory extends DefaultCacheFactory{
	
	public void init(){
		super.init();
		map.put(CacheType.LOCAL, new EhcacheMapCacheFactory());
	}

}

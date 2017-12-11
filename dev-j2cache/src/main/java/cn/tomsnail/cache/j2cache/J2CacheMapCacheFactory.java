package cn.tomsnail.cache.j2cache;

import cn.tomsnail.cache.core.CacheConfig;
import cn.tomsnail.cache.core.ICache;
import cn.tomsnail.cache.core.ICacheFactory;

public class J2CacheMapCacheFactory implements ICacheFactory{

	@Override
	public ICache getCache() {
		CacheConfig cacheConfig = new CacheConfig();
		cacheConfig.setName("DefaultCacheScheme");
		return getCache(cacheConfig);
	}

	@Override
	public ICache getCache(CacheConfig cacheConfig) {
		return new J2CacheMapCache(cacheConfig);
	}

}

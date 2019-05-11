package cn.tomsnail.snail.e3.j2cache;

import cn.tomsnail.snail.ext.cache.core.CacheConfig;
import cn.tomsnail.snail.ext.cache.core.ICache;
import cn.tomsnail.snail.ext.cache.core.ICacheFactory;

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

package cn.tomsnail.snail.e3.ehcache3;

import cn.tomsnail.snail.ext.cache.core.CacheConfig;
import cn.tomsnail.snail.ext.cache.core.ICache;
import cn.tomsnail.snail.ext.cache.core.ICacheFactory;

/**
 *        ehcache缓存工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 下午3:15:45
 * @see 
 */
public class EhcacheMapCacheFactory implements ICacheFactory{
	

	@Override
	public ICache getCache() {
		CacheConfig cacheConfig = new CacheConfig();
		cacheConfig.setName("DefaultCacheScheme");
		return getCache(cacheConfig);
	}

	@Override
	public ICache getCache(CacheConfig cacheConfig) {
		return new EhcacheMapCache(cacheConfig);
	}

}

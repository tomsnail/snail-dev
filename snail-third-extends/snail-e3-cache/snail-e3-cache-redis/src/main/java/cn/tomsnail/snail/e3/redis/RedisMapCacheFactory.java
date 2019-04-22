package cn.tomsnail.snail.e3.redis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.tomsnail.snail.ext.cache.core.CacheConfig;
import cn.tomsnail.snail.ext.cache.core.ICache;
import cn.tomsnail.snail.ext.cache.core.ICacheFactory;


/**
 *        redis缓存工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 下午4:28:51
 * @see 
 */
public class RedisMapCacheFactory implements ICacheFactory {
	
	private static final Map<String,RedisMapCache> MAP = new ConcurrentHashMap<String, RedisMapCache>();

	@Override
	public ICache getCache() {
		return null;
	}

	@Override
	public ICache getCache(CacheConfig cacheConfig) {
		if(cacheConfig==null||cacheConfig.getUrl()==null){
			return null;
		}
		synchronized (MAP) {
			if(MAP.containsKey(cacheConfig.getUrl())){
				
			}else{
				RedisMapCache redisMapCache = new RedisMapCache(cacheConfig);
				MAP.put(cacheConfig.getUrl(), redisMapCache);
			}
		}
		return MAP.get(cacheConfig.getUrl());
	}

}

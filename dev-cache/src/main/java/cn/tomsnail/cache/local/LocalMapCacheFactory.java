package cn.tomsnail.cache.local;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.tomsnail.cache.core.CacheConfig;
import cn.tomsnail.cache.core.ICache;
import cn.tomsnail.cache.core.ICacheFactory;

/**
 *        本地缓存工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 上午11:44:42
 * @see 
 */
public class LocalMapCacheFactory implements ICacheFactory {
	
	private static final Map<String,LocalMapCache> MAP = new HashMap<String,LocalMapCache>();
	
	private static final Lock _lock = new ReentrantLock();
	
	@Override
	public ICache getCache() {
		CacheConfig cacheConfig = new CacheConfig();
		return getCache(cacheConfig);
	}

	@Override
	public ICache getCache(CacheConfig cacheConfig) {
		if(cacheConfig==null||cacheConfig.getName()==null){
			return null;
		}
		_lock.lock();
		if(MAP.containsKey(cacheConfig.getName())){
			 
		}else{
			LocalMapCache localMapCache = new LocalMapCache();
			MAP.put(cacheConfig.getName(),localMapCache);
			new Thread(localMapCache).start();
		}
		_lock.unlock();
		return MAP.get(cacheConfig.getName());
	}

}

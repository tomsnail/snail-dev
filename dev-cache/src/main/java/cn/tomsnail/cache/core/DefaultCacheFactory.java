package cn.tomsnail.cache.core;

import java.util.HashMap;
import java.util.Map;

import cn.tomsnail.cache.local.LocalMapCacheFactory;

/**
 *        默认缓存工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 上午11:39:14
 * @see 
 */
public class DefaultCacheFactory implements ICacheFactory{

	protected Map<CacheType,ICacheFactory> map = new HashMap<CacheType, ICacheFactory>();
	
	public DefaultCacheFactory(){
		init();
	}
	
	@Override
	public ICache getCache() {
		CacheConfig cacheConfig = new CacheConfig();
		return getCache(cacheConfig);
	}
	
	@Override
	public ICache getCache(CacheConfig cacheConfig){
		if(!map.containsKey(cacheConfig.getCacheType())){
			return null;
		}
		return map.get(cacheConfig.getCacheType()).getCache(cacheConfig);
	}
	
	public void init(){
		map.put(CacheType.LOCAL, new LocalMapCacheFactory());
		try {
			map.put(CacheType.REDIS, (ICacheFactory) Class.forName("cn.tomsnail.redis.RedisMapCacheFactory").newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			map.put(CacheType.EHCACHE, (ICacheFactory) Class.forName("cn.tomsnail.ehcache.EhcacheMapCacheFactory").newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<CacheType, ICacheFactory> getMap() {
		return map;
	}

	public void setMap(Map<CacheType, ICacheFactory> map) {
		this.map = map;
	}
	
	

}

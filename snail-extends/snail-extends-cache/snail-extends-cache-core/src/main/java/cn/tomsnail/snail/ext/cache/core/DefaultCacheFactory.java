package cn.tomsnail.snail.ext.cache.core;

import java.util.HashMap;
import java.util.Map;

import cn.tomsnail.snail.ext.cache.local.LocalMapCacheFactory;


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
			Class clazz=  Class.forName("cn.tomsnail.snail.e3.redis.RedisMapCacheFactory");
			if(clazz!=null)
				map.put(CacheType.REDIS, (ICacheFactory) clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class clazz=  Class.forName("cn.tomsnail.snail.e3.ehcache.EhcacheMapCacheFactory");
			if(clazz!=null)
				map.put(CacheType.EHCACHE, (ICacheFactory) clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class clazz=  Class.forName("cn.tomsnail.snail.e3.ehcache3.EhcacheMapCacheFactory");
			if(clazz!=null)
				map.put(CacheType.EHCACHE3, (ICacheFactory) clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class clazz=  Class.forName("cn.tomsnail.snail.e3.j2cache.J2CacheMapCacheFactory");
			if(clazz!=null)
				map.put(CacheType.J2CACHE, (ICacheFactory) clazz.newInstance());
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

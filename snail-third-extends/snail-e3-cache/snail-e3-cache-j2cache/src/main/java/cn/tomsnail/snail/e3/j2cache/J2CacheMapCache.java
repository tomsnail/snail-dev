package cn.tomsnail.snail.e3.j2cache;

import cn.tomsnail.snail.ext.cache.core.CacheConfig;
import cn.tomsnail.snail.ext.cache.core.ICache;
import cn.tomsnail.snail.ext.cache.core.IDestoryCache;
import cn.tomsnail.snail.ext.cache.core.IInitCache;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;

public class J2CacheMapCache implements ICache,IInitCache,IDestoryCache{
	
	
	private CacheConfig cacheConfig;
	
	private volatile static CacheChannel cache;
	
	private static final String DEFAULT_REGIN = "J2Cache_User_Cache";
	
	public J2CacheMapCache(CacheConfig cacheConfig) {
		super();
		this.cacheConfig = cacheConfig;
		init();
	}

	@Override
	public void destory() {
		cache.close();
	}

	@Override
	public void init() {
		if(cache==null){
			synchronized (cacheConfig) {
				if(cache==null){
					cache = J2Cache.getChannel();
				}
			}
		}
	}

	@Override
	public void set(String key, Object value) {
		cache.set(DEFAULT_REGIN,key, value);
		
	}

	@Override
	public void set(String key, Object value, long expire) {
		cache.set(DEFAULT_REGIN,key, value,(int)expire/1000);
		
	}

	@Override
	public long getExpire(String key) {
		return 0;
	}

	@Override
	public Object get(String key) {
		return cache.get(DEFAULT_REGIN, key);
	}

	@Override
	public Object remove(String key) {
		cache.evict(DEFAULT_REGIN, key);
		return null;
	}

	@Override
	public boolean isExits(String key) {
		return cache.get(DEFAULT_REGIN, key)==null;
	}

}

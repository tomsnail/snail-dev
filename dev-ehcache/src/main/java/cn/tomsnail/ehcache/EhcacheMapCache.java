package cn.tomsnail.ehcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import cn.tomsnail.cache.core.CacheConfig;
import cn.tomsnail.cache.core.ICache;
import cn.tomsnail.cache.core.IDestoryCache;
import cn.tomsnail.cache.core.IInitCache;

/**
 *        EhcacheMapCache
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 下午3:15:35
 * @see 
 */
public class EhcacheMapCache implements ICache,IInitCache,IDestoryCache{

	private volatile static CacheManager manager;
	
	private CacheConfig cacheConfig;
	
	private static final Map<String,Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
	
	public EhcacheMapCache(CacheConfig cacheConfig){
		this.cacheConfig = cacheConfig;
		init();
	}
	
	@Override
	public void set(String key, Object value) {
		set(key,value,1000l*60*60*24*7);
	}

	@Override
	public void set(String key, Object value, long expire) {
		if(expire<=0){
			expire = 3600*6;
		}
		setValue(cacheConfig.getName(),key,value,false,(int)expire,0);
	}
	
	private void setValue(String name, String key, Object value, boolean eternal,int liveTime,int idleTime){
		Element element = new Element(key, value);
		element.setEternal(eternal);
		element.setTimeToIdle(idleTime);
		element.setTimeToLive(liveTime);
		if(!cacheMap.containsKey(name)){
			 Cache cache=new Cache(name, 1, true, true, liveTime, idleTime); 
			 cacheMap.put(name, cache);
			 manager.addCache(cache);
		}
		cacheMap.get(name).put(element);
	}

	@Override
	public long getExpire(String key) {
		Element element = cacheMap.get(cacheConfig.getName()).get(key);
		if(element==null){
			return -1l;
		}else{
			return cacheMap.get(cacheConfig.getName()).get(key).getTimeToLive();
		}
	}

	@Override
	public Object get(String key) {
		Element element = cacheMap.get(cacheConfig.getName()).get(key);
		if(element==null){
			return null;
		}else{
			return cacheMap.get(cacheConfig.getName()).get(key).getObjectValue();
		}
	}

	@Override
	public Object remove(String key) {
		Object obj = get(key);
		if(cacheMap.get(cacheConfig.getName()).remove(key)){
			return obj;
		}
		return null;
	}

	@Override
	public boolean isExits(String key) {
		return cacheMap.get(cacheConfig.getName()).isElementInMemory(key)?true: cacheMap.get(cacheConfig.getName()).isElementOnDisk(key);

	}

	@Override
	public void destory() {
		cacheMap.get(cacheConfig.getName()).removeAll();
		cacheConfig = null;
	}

	@Override
	public void init() {
		if(manager==null){
			synchronized (cacheConfig) {
				if(manager==null){
					if(cacheConfig.getUrl()!=null){
						manager = new CacheManager(cacheConfig.getUrl());
					}else{
						manager = CacheManager.create();
					}
				}
			}
		}		
	}

}

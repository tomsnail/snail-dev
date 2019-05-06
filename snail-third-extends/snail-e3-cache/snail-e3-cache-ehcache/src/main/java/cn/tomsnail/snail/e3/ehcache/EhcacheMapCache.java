package cn.tomsnail.snail.e3.ehcache;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.ext.cache.core.CacheConfig;
import cn.tomsnail.snail.ext.cache.core.ICache;
import cn.tomsnail.snail.ext.cache.core.IDestoryCache;
import cn.tomsnail.snail.ext.cache.core.IInitCache;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;


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
	
	private ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
	
	public EhcacheMapCache(CacheConfig cacheConfig){
		this.cacheConfig = cacheConfig;
		init();
	}
	
	@Override
	public void set(String key, Object value) {
		set(key,value,1000L*60*60*24*7);
	}

	@Override
	public void set(String key, Object value, long expire) {
		if(expire<=0){
			expire = 3600L*6;
		}
		setValue(cacheConfig.getName(),key,value,false,(int)expire,0);
	}
	
	private  void setValue(String name, String key, Object value, boolean eternal,int liveTime,int idleTime){
		LOCK.writeLock().lock();
		try {
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
		} catch (RuntimeException e) {
			throw e;
		}finally {
			LOCK.writeLock().unlock();
		}
		
	}

	@Override
	public long getExpire(String key) {
		Element element = getElement(key);
		if(element==null){
			return -1L;
		}else{
			return element.getTimeToLive();
		}
	}

	@Override
	public Object get(String key) {
		Element element = getElement(key);
		if(element!=null){
			return element.getObjectValue();
		}
		return null;
	}
	
	private Element getElement(String key) {
		LOCK.readLock().lock();
		try {
			if(!cacheMap.containsKey(cacheConfig.getName())){
				return null;
			}
			Element element = null;
			Cache cache = cacheMap.get(cacheConfig.getName());
			if(cache!=null&&cache.getStatus().equals(Status.STATUS_ALIVE)) {
				element = cache.get(key);
			}
			return element;
		} catch (RuntimeException e) {
			throw e;
		}finally {
			LOCK.readLock().unlock();
		}
	}

	@Override
	public Object remove(String key) {
		Element element = getElement(key);
		if(element==null) return null;
		Object obj =  element.getObjectValue();
		Cache cache = cacheMap.get(cacheConfig.getName());
		if(cache==null) return null;
		cache.remove(key);
		return obj;
	}

	@Override
	public boolean isExits(String key) {
		LOCK.readLock().lock();
		try {
			if(!cacheMap.containsKey(cacheConfig.getName())){
				return false;
			}
			Cache cache = cacheMap.get(cacheConfig.getName());
			if(cache!=null&&cache.getStatus().equals(Status.STATUS_ALIVE)) {
				return cache.isElementInMemory(key)||cache.isElementOnDisk(key);
			}
			return false;
		} catch (RuntimeException e) {
			throw e;
		}finally {
			LOCK.readLock().unlock();
		}
	}

	@Override
	public void destory() {
		if(!cacheMap.containsKey(cacheConfig.getName())){
			return;
		}
		cacheMap.get(cacheConfig.getName()).removeAll();
		cacheConfig = null;
	}

	@Override
	public void init() {
		if(manager==null){
			synchronized (cacheConfig) {
				if(manager==null){
					String _url = cacheConfig.getUrl();
					if(StringUtils.isBlank(_url)) {
						_url = ConfigHelp.getInstance("config").getLocalConfig("system.cache.ehcache.url", "");
					}
					if(StringUtils.isBlank(_url)) {
						manager = CacheManager.create();
						return;
					}
					try {
						File file = new File(_url);
						if(file.exists()&&file.isFile()) {
							manager = new CacheManager(cacheConfig.getUrl());
						}else {
							URL url = getClass().getClassLoader().getResource(_url);
							if(url!=null) {
								manager = new CacheManager(url);
							}else {
								manager = CacheManager.create();
							}
						}
					} catch (Exception e) {
						manager = CacheManager.create();
					}
				}
			}
		}		
	}

}

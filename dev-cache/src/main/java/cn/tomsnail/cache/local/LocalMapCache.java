package cn.tomsnail.cache.local;

import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.tomsnail.cache.core.AClearedCache;
import cn.tomsnail.cache.core.IDestoryCache;
import cn.tomsnail.cache.core.IInitCache;

/**
 *        本地缓存类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 上午11:41:00
 * @see 
 */
public class LocalMapCache extends AClearedCache implements IInitCache,IDestoryCache{
	
	private Map<String,SoftReference<MapCacheObject<Object>>> _map = new ConcurrentHashMap<String,SoftReference<MapCacheObject<Object>>>();
	

	@Override
	public void set(String key, Object value) {
		set(key,value,1000l*60*60*24*7);
	}

	@Override
	public void set(String key, Object value, long expire) {
		SoftReference<MapCacheObject<Object>> softReference = new SoftReference<MapCacheObject<Object>>(new MapCacheObject<>(value,expire,System.currentTimeMillis()));
		_map.put(key, softReference);
	}

	@Override
	public long getExpire(String key) {
		SoftReference<MapCacheObject<Object>> reference= _map.get(key);
		if(reference!=null){
			MapCacheObject<Object> mapCacheObject = reference.get();
			if(mapCacheObject!=null){
				return mapCacheObject.getExpire();
			}		
		}
		return -1;
	
	}

	@Override
	public Object get(String key) {
		SoftReference<MapCacheObject<Object>> reference= _map.get(key);
		if(reference!=null){
			MapCacheObject<Object> mapCacheObject = reference.get();
			if(mapCacheObject!=null){
				return mapCacheObject.getT();
			}		
		}
		return null;
	}
	
	@Override
	public Object remove(String key) {
		return _map.remove(key);
	}


	@Override
	public boolean isExits(String key) {
		return _map.containsKey(key);
	}

	@Override
	public void destory() {
		if(_map!=null)
			_map.clear();
		_map = null;
		this.setCleared(false);
		this.setRuning(false);
	}

	@Override
	public void clean() {
		Iterator<String> it = _map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			SoftReference<MapCacheObject<Object>> reference= _map.get(key);
			MapCacheObject<Object> mapCacheObject = _map.get(key).get();
			if(mapCacheObject!=null){
				Long expire = mapCacheObject.getExpire();
				if(mapCacheObject.getLastTime()+expire<=System.currentTimeMillis()){
					_map.remove(key);
				}
			}else{
				_map.remove(key);
			}
		}
	}

	@Override
	public void init() {
		if(_map==null){
			 _map = new ConcurrentHashMap<String,SoftReference<MapCacheObject<Object>>>();
		}
		_map.clear();
	}

	
}

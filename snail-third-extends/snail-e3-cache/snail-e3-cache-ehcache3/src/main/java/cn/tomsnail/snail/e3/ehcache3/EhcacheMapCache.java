package cn.tomsnail.snail.e3.ehcache3;


import java.io.File;
import java.net.URL;

import cn.tomsnail.snail.core.util.JsonUtil;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.ext.cache.core.CacheConfig;
import cn.tomsnail.snail.ext.cache.core.ICache;
import cn.tomsnail.snail.ext.cache.core.IDestoryCache;
import cn.tomsnail.snail.ext.cache.core.IInitCache;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;

import org.ehcache.xml.XmlConfiguration;


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
		Cache<String,String> cache = getCache();
		System.out.println(cache);
		if(cache !=null) {
			cache.put(key, JsonUtil.toJson(value));
		}
		
		
	}

	@Override
	public long getExpire(String key) {
		//Cache<String,String> cache = getCache();
		return 0;
	}
	
	private Cache<String,String> getCache(){
		return manager.getCache(cacheConfig.getName(), String.class, String.class);
	}
	
	

	@Override
	public Object get(String key) {
		
		Cache<String,String> cache = getCache();
		
		if(cache!=null) {
			return cache.get(key);
		}
		
		return null;
	}
	


	@Override
	public Object remove(String key) {
		Cache<String,String> cache = getCache();
		
		if(cache!=null) {
			 cache.remove(key);
		}
		return null;
	}

	@Override
	public boolean isExits(String key) {
		
		Cache<String,String> cache = getCache();
		
		if(cache!=null) {
			return cache.containsKey(key);
		}
		
		return false;
	}

	@Override
	public void destory() {
		
		Cache<String,String> cache = getCache();
		
		if(cache!=null) {
			cache.clear();
			manager.removeCache(cacheConfig.getName());
		}
		

	}

	@Override
	public void init() {
		if(manager==null){
			synchronized (cacheConfig) {
				if(manager==null){
					String _url = cacheConfig.getUrl();
					if(StringUtils.isBlank(_url)) {
						_url = ConfigHelp.getInstance("config").getLocalConfig("system.cache.ehcache3.url", "");
					}
					if(StringUtils.isBlank(_url)) {
						manager = CacheManagerBuilder.newCacheManagerBuilder().build();
						manager.init();
						return;
					}
					try {
						
						File file = new File(_url);
						if(file.exists()&&file.isFile()) {
							Configuration xmlConf = new XmlConfiguration(file.toURL());
							manager = CacheManagerBuilder.newCacheManager(xmlConf);
						}else {
							
							URL url = getClass().getClassLoader().getResource(_url);
							if(url!=null) {
								Configuration xmlConf = new XmlConfiguration(url);
								manager = CacheManagerBuilder.newCacheManager(xmlConf);
							}else {
								manager = CacheManagerBuilder.newCacheManagerBuilder().build();
							}
						}
					} catch (Exception e) {
						manager = CacheManagerBuilder.newCacheManagerBuilder().build();
					}
					manager.init();
				}
			}
		}		
		
		
	}

}

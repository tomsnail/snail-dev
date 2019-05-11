package net.oschina.j2cache.cache.support.util;

import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;

import net.oschina.j2cache.J2CacheConfig;

public class SpringJ2CacheConfigUtil {

	/**
	 * 从spring环境变量中查找j2cache配置
	 */
	public final static J2CacheConfig initFromConfig(StandardEnvironment environment){
		J2CacheConfig config = new J2CacheConfig();
		config.setSerialization(environment.getProperty("system.cache.j2cache.serialization"));
		config.setBroadcast(environment.getProperty("system.cache.j2cache.broadcast"));
		config.setL1CacheName(environment.getProperty("system.cache.j2cache.L1.provider_class"));
		config.setL2CacheName(environment.getProperty("system.cache.j2cache.L2.provider_class"));
		config.setSyncTtlToRedis(!"false".equalsIgnoreCase(environment.getProperty("system.cache.j2cache.sync_ttl_to_redis")));
		config.setDefaultCacheNullObject("true".equalsIgnoreCase(environment.getProperty("system.cache.j2cache.default_cache_null_object")));
		String l2_config_section = environment.getProperty("system.cache.j2cache.L2.config_section");
		if (l2_config_section == null || l2_config_section.trim().equals(""))
			l2_config_section = config.getL2CacheName();
		final String l2_section = l2_config_section;
		environment.getPropertySources().forEach(a -> {
			if(a instanceof MapPropertySource) {
				MapPropertySource c = (MapPropertySource) a;
				c.getSource().forEach((k,v) -> {
					String key = k;
					if (key.startsWith("system.cache.j2cache."+config.getBroadcast() + ".")) {
						config.getBroadcastProperties().setProperty(key.substring((config.getBroadcast() + ".").length()),
								environment.getProperty(key));
					}	
					if (key.startsWith("system.cache.j2cache."+config.getL1CacheName() + ".")) {
						config.getL1CacheProperties().setProperty(key.substring((config.getL1CacheName() + ".").length()),
								environment.getProperty(key));
					}
					if (key.startsWith("system.cache.j2cache."+l2_section + ".")) {
						config.getL2CacheProperties().setProperty(key.substring((l2_section + ".").length()),
								environment.getProperty(key));
					}
				});
			}
			if(a instanceof CompositePropertySource) {
				CompositePropertySource c = (CompositePropertySource) a;
				String[] keys = c.getPropertyNames();
				for(String key:keys){
					if (key.startsWith("system.cache.j2cache."+config.getBroadcast() + ".")) {
						config.getBroadcastProperties().setProperty(key.substring(("system.cache.j2cache."+config.getBroadcast() + ".").length()),
								environment.getProperty(key));
					}
					if (key.startsWith("system.cache.j2cache."+config.getL1CacheName() + ".")) {
						config.getL1CacheProperties().setProperty(key.substring(("system.cache.j2cache."+config.getL1CacheName() + ".").length()),
								environment.getProperty(key));
					}
					if (key.startsWith("system.cache.j2cache."+l2_section + ".")) {
						config.getL2CacheProperties().setProperty(key.substring(("system.cache.j2cache."+l2_section + ".").length()),
								environment.getProperty(key));
					}
				}
			}
		});
		return config;
	}
}

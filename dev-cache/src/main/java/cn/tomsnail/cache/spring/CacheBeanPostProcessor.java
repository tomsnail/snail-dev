package cn.tomsnail.cache.spring;

import java.lang.reflect.Field;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import cn.tomsnail.cache.annotation.CacheClassd;
import cn.tomsnail.cache.annotation.CacheConfig;
import cn.tomsnail.cache.core.CacheType;
import cn.tomsnail.cache.core.DefaultCacheFactory;
import cn.tomsnail.cache.core.ICache;
import cn.tomsnail.cache.core.ICacheFactory;
import cn.tomsnail.config.client.plugin.AnnotationConverter;
import cn.tomsnail.util.string.StringUtils;

/**
 *        缓存spring引用初始化类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 下午5:21:44
 * @see 
 */
@Component
public class CacheBeanPostProcessor implements BeanPostProcessor{
	
	private ICacheFactory cacheFactory = new DefaultCacheFactory();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		Class clazz = bean.getClass();
		if(clazz.isAnnotationPresent(CacheClassd.class)){
			Field[] fs = clazz.getDeclaredFields();
			for(Field f:fs){
				boolean needCache = f.getType().getCanonicalName().startsWith("cn.tomsnail.cache.core.ICache")&&f.isAnnotationPresent(CacheConfig.class);
				if(needCache){
					CacheConfig acacheConfig = f.getAnnotation(CacheConfig.class);
					cn.tomsnail.cache.core.CacheConfig cacheConfig = new cn.tomsnail.cache.core.CacheConfig();
					if(StringUtils.isNotBlank(acacheConfig.cachedType())){
						String cacheType = AnnotationConverter.getValue(acacheConfig.cachedType());
						if("ehcache".equalsIgnoreCase(cacheType)){
							cacheConfig.setCacheType(CacheType.EHCACHE);
						}else if("redis".equalsIgnoreCase(cacheType)){
							cacheConfig.setCacheType(CacheType.REDIS);
						}else{
							cacheConfig.setCacheType(CacheType.LOCAL);
						}
					}else{
						cacheConfig.setCacheType(acacheConfig.cacheType());
					}
					if(NumberUtils.isDigits( AnnotationConverter.getValue(acacheConfig.expire()))){
						cacheConfig.setExpire(AnnotationConverter.getValue(Long.valueOf( AnnotationConverter.getValue(acacheConfig.expire()))));
					}else{
						cacheConfig.setExpire(1000l);
					}
					cacheConfig.setName(AnnotationConverter.getValue( AnnotationConverter.getValue(acacheConfig.name())));
					cacheConfig.setPassword(AnnotationConverter.getValue( AnnotationConverter.getValue(acacheConfig.password())));
					if(NumberUtils.isDigits( AnnotationConverter.getValue(acacheConfig.port()))){
						cacheConfig.setPort(AnnotationConverter.getValue(Integer.valueOf( AnnotationConverter.getValue(acacheConfig.port()))));
					}else{
						cacheConfig.setPort(0);
					}
					if(NumberUtils.isDigits( AnnotationConverter.getValue(acacheConfig.timeout()))){
						cacheConfig.setTimeout(AnnotationConverter.getValue(Long.valueOf( AnnotationConverter.getValue(acacheConfig.timeout()))));
					}else{
						cacheConfig.setTimeout(10000l);
					}
					cacheConfig.setUrl(AnnotationConverter.getValue( AnnotationConverter.getValue(acacheConfig.url())));
					cacheConfig.setUsername(AnnotationConverter.getValue( AnnotationConverter.getValue(acacheConfig.username())));
					ICache cache = cacheFactory.getCache(cacheConfig);
					f.setAccessible(true);
					try {
						f.set(bean, cache);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public ICacheFactory getCacheFactory() {
		return cacheFactory;
	}

	public void setCacheFactory(ICacheFactory cacheFactory) {
		this.cacheFactory = cacheFactory;
	}

}

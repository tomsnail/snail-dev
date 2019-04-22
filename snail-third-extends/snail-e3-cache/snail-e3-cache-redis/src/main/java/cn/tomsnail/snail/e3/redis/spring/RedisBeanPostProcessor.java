package cn.tomsnail.snail.e3.redis.spring;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.config.client.plugin.AnnotationConverter;
import cn.tomsnail.snail.e3.redis.annotation.RedisAConfig;
import cn.tomsnail.snail.e3.redis.core.RedisConfig;
import cn.tomsnail.snail.e3.redis.core.RedisFactory;
import redis.clients.jedis.Jedis;


/**
 *        spring初始化redis属性
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午1:52:07
 * @see 
 */
@Component
public class RedisBeanPostProcessor implements BeanPostProcessor{

	private RedisFactory redisFactory = new RedisFactory();
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		Class clazz = bean.getClass();
		Field[] fs = clazz.getDeclaredFields();
		for(Field f:fs){
			if(f.getType().getCanonicalName().startsWith("org.springframework.data.redis.core.RedisTemplate")&&f.isAnnotationPresent(RedisAConfig.class)){
				RedisAConfig redisAConfig = f.getAnnotation(RedisAConfig.class);
				RedisConfig redisConfig = new RedisConfig();
				redisConfig.setMaxIdle(AnnotationConverter.getValue(redisAConfig.maxIdle()));
				redisConfig.setMaxTotal(AnnotationConverter.getValue(redisAConfig.maxTotal()));
				redisConfig.setMaxWaitMillis(AnnotationConverter.getValue(redisAConfig.maxWaitMillis()));
				redisConfig.setPassword(AnnotationConverter.getValue(redisAConfig.password()));
				redisConfig.setPort(AnnotationConverter.getValue(redisAConfig.port()));
				redisConfig.setTestOnBorrow(AnnotationConverter.getValue(redisAConfig.testOnBorrow()));
				redisConfig.setUrl(AnnotationConverter.getValue(redisAConfig.url()));
				RedisTemplate<String, Object> redisTemplate = redisFactory.getRedisTemplate(redisConfig);
				f.setAccessible(true);
				try {
					f.set(bean, redisTemplate);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(f.getType().getCanonicalName().startsWith("redis.clients.jedis.Jedis")&&f.isAnnotationPresent(RedisAConfig.class)){
				RedisAConfig redisAConfig = f.getAnnotation(RedisAConfig.class);
				RedisConfig redisConfig = new RedisConfig();
				redisConfig.setMaxIdle(AnnotationConverter.getValue(redisAConfig.maxIdle()));
				redisConfig.setMaxTotal(AnnotationConverter.getValue(redisAConfig.maxTotal()));
				redisConfig.setMaxWaitMillis(AnnotationConverter.getValue(redisAConfig.maxWaitMillis()));
				redisConfig.setPassword(AnnotationConverter.getValue(redisAConfig.password()));
				redisConfig.setPort(AnnotationConverter.getValue(redisAConfig.port()));
				redisConfig.setTestOnBorrow(AnnotationConverter.getValue(redisAConfig.testOnBorrow()));
				redisConfig.setUrl(AnnotationConverter.getValue(redisAConfig.url()));
				Jedis jedis = redisFactory.getJedis(redisConfig);
				f.setAccessible(true);
				try {
					f.set(bean, jedis);
				} catch (Exception e) {
					e.printStackTrace();
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

}

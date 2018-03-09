package cn.tomsnail.redis.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 *        redis工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午1:40:16
 * @see 
 */
public class RedisFactory{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisFactory.class);
	
	private static final Map<String,RedisTemplate<String, Object>> REDIS_MAP = new ConcurrentHashMap<String, RedisTemplate<String, Object>>(); 
	
	private static final Map<String,JedisPool> JEDIS_POOL_MAP = new ConcurrentHashMap<String,JedisPool>();

	public synchronized RedisTemplate<String, Object> getRedisTemplate(RedisConfig redisConfig){
		try {
			if(REDIS_MAP.containsKey(redisConfig.getUrl())){
			}else{
				JedisPoolConfig poolConfig = new JedisPoolConfig();
				poolConfig.setMaxIdle(redisConfig.getMaxIdle());
				poolConfig.setMaxTotal(redisConfig.getMaxTotal());
				poolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
				poolConfig.setTestOnBorrow(redisConfig.isTestOnBorrow());
				JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
				connectionFactory.setPort(redisConfig.getPort());
				connectionFactory.setHostName(redisConfig.getUrl());
				if(StringUtils.isBlank(redisConfig.getPassword())){
					connectionFactory.setPassword(redisConfig.getPassword());
				}
				connectionFactory.setUsePool(true);
				connectionFactory.afterPropertiesSet();
				RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
				redisTemplate.setConnectionFactory(connectionFactory);
				redisTemplate.afterPropertiesSet();
				redisTemplate.setStringSerializer(new StringRedisSerializer());
				redisTemplate.setKeySerializer(new StringRedisSerializer());
				redisTemplate.setValueSerializer(new StringRedisSerializer());
				redisTemplate.setHashValueSerializer(new StringRedisSerializer());
				redisTemplate.setHashKeySerializer(new StringRedisSerializer());
				REDIS_MAP.put(redisConfig.getUrl(), redisTemplate);
			}
			return REDIS_MAP.get(redisConfig.getUrl());
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return null;
	}
	
	public synchronized JedisConnectionFactory getJedisConnectionFactory(RedisConfig redisConfig){
		try {
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxIdle(redisConfig.getMaxIdle());
			poolConfig.setMaxTotal(redisConfig.getMaxTotal());
			poolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
			poolConfig.setTestOnBorrow(redisConfig.isTestOnBorrow());
			JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
			connectionFactory.setPort(redisConfig.getPort());
			connectionFactory.setHostName(redisConfig.getUrl());
			if(StringUtils.isBlank(redisConfig.getPassword())){
				connectionFactory.setPassword(redisConfig.getPassword());
			}
			connectionFactory.setUsePool(true);
			connectionFactory.afterPropertiesSet();
			return connectionFactory;
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return null;
	}
	
	public synchronized  Jedis getJedis(RedisConfig redisConfig){
		try {			
			if(JEDIS_POOL_MAP.containsKey(redisConfig.getUrl())){
			}else{
				JedisPoolConfig poolConfig = new JedisPoolConfig();
				poolConfig.setMaxIdle(300);
				poolConfig.setMaxTotal(100);
				poolConfig.setMaxWaitMillis(1000);
				poolConfig.setTestOnBorrow(true);
				int port = Integer.valueOf(redisConfig.getPort());
				JedisPool jedisPool = new JedisPool(poolConfig,redisConfig.getUrl(), port);
				JEDIS_POOL_MAP.put(redisConfig.getUrl(), jedisPool);
			}
			return JEDIS_POOL_MAP.get(redisConfig.getUrl()).getResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

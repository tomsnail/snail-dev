package cn.tomsnail.redis;


import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import cn.tomsnail.cache.core.CacheConfig;
import cn.tomsnail.cache.core.ICache;
import cn.tomsnail.cache.core.IDestoryCache;
import cn.tomsnail.cache.core.IInitCache;
import cn.tomsnail.redis.core.RedisTemplate;

/**
 *        redis缓存
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 下午3:51:00
 * @see 
 */
public class RedisMapCache  implements ICache,IInitCache,IDestoryCache {
			
	private RedisTemplate	<String	, Object> redisTemplate = new RedisTemplate<String	, Object>();

	public RedisMapCache(CacheConfig cacheConfig){
		RedisSerializer stringSerializer = new StringRedisSerializer();
	    redisTemplate.setKeySerializer(stringSerializer);
	    redisTemplate.setValueSerializer(stringSerializer);
	    redisTemplate.setHashKeySerializer(stringSerializer);
	    redisTemplate.setHashValueSerializer(stringSerializer);
		redisTemplate.setUrl(cacheConfig.getUrl());
		redisTemplate.setPassword(cacheConfig.getPassword());
		redisTemplate.setPort(cacheConfig.getPort());
		redisTemplate.afterPropertiesSet();
	}
	
	@Override
	public void destory() {
	}

	@Override
	public void init() {
	}

	@Override
	public void set(String key, Object value) {
		set(key,value,1000l*60*60*24*7);
	}

	@Override
	public void set(String key, Object value, long expire) {
		redisTemplate.boundValueOps(key).set(value, expire, TimeUnit.MILLISECONDS);
	}

	@Override
	public long getExpire(String key) {
		return redisTemplate.boundValueOps(key).getExpire();
	}

	@Override
	public Object get(String key) {
		return redisTemplate.boundValueOps(key).get();
	}

	@Override
	public Object remove(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() 
		 {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                result = connection.del(key.getBytes());
                return result;
            }
        });
		
	}

	@Override
	public boolean isExits(final String key) {
		 return redisTemplate.execute(new RedisCallback<Boolean>() {
	            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
	                return connection.exists(key.getBytes());
	            }
	     });
	}

}

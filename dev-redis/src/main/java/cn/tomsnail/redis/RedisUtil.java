package cn.tomsnail.redis;

import redis.clients.jedis.Jedis;
import cn.tomsnail.redis.core.RedisConfig;

public class RedisUtil {

	
	public static void set(String key,String value,RedisConfig redisConfig){
		set(key,value,0,redisConfig);
	}
	
	public static void set(String key,String value,int timeout,RedisConfig redisConfig){
		Jedis jedis = getJedis(redisConfig);
		jedis.set(key, value);
		if(timeout>0){
			jedis.expire(key, timeout);
		}
		colse(jedis);
	}
	
	
	public static String get(String key,RedisConfig redisConfig ){
		Jedis jedis = getJedis(redisConfig);
		String v = jedis.get(key);
		colse(jedis);
		return v;
	}
	
	
	private static Jedis getJedis(RedisConfig redisConfig ){
		if(redisConfig==null){
			throw new NullPointerException("RedisConfig is null");
		}
		Jedis jedis = new Jedis(redisConfig.getUrl(), redisConfig.getPort(), redisConfig.getMaxWaitMillis());
		return jedis;
	}
	
	private static void colse(Jedis jedis){
		if(jedis!=null){
			jedis.close();
		}
	}
	
}

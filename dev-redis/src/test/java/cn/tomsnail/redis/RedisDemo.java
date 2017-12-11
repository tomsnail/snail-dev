package cn.tomsnail.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import cn.tomsnail.redis.annotation.RedisAConfig;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午2:08:07
 * @see 
 */
@Component
public class RedisDemo {

	@RedisAConfig(url="192.168.169.170")
	private RedisTemplate<String, Object> redisTemplate;
	
	@RedisAConfig(url="192.168.169.170")
	private Jedis jedis;
	
	public void demo(){
		redisTemplate.boundValueOps("UrlSign1").expire(1, TimeUnit.SECONDS);
		System.out.println(redisTemplate.boundValueOps("UrlSign1").get());
	}
	
}

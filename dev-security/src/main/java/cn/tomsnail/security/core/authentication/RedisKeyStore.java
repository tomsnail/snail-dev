package cn.tomsnail.security.core.authentication;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *        基于redis的key存储
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午2:19:43
 * @see 
 */
public class RedisKeyStore implements IKeyStore{

	
	private RedisTemplate<String, String> redisTemplate;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	
	@Override
	public void save(Key key) {
		
		if(redisTemplate==null)return;
		
		try {
			String keyJson = objectMapper.writeValueAsString(key);
			redisTemplate.boundValueOps("TOKEN_KEY_"+key.getKey()).set(keyJson, key.getExpire(), TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Key get(String key) {
		if(redisTemplate==null)return new Key();
		String keyJson = redisTemplate.boundValueOps("TOKEN_KEY_"+key).get();
		if(StringUtils.isBlank(keyJson)){
			return null;
		}
		try {
			return objectMapper.readValue(keyJson, Key.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	

}

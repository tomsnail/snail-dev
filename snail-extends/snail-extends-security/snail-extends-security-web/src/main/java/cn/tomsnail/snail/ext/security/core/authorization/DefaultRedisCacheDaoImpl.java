package cn.tomsnail.snail.ext.security.core.authorization;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;

import cn.tomsnail.snail.ext.security.core.authorization.model.TRole;
import cn.tomsnail.snail.ext.security.core.authorization.model.TUser;


/**
 *        默认redis鉴权服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午5:16:00
 * @see 
 */
public class DefaultRedisCacheDaoImpl implements ICacheDao{
	
	private RedisTemplate<String, String> redisTemplate;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public TRole getRoleByUser(TUser user) {
		try {
			return objectMapper.readValue(redisTemplate.boundValueOps(user.getUuid()).get(),TRole.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveUserRole(TUser user, TRole role) {
		try {
			String json = objectMapper.writeValueAsString(role);
			redisTemplate.boundValueOps(user.getUuid()).set(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	

}

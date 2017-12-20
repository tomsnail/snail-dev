package cn.tomsnail.config.client;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import cn.tomsnail.starter.domain.spring.SpringBeanUtil;
import cn.tomsnail.util.string.StringUtils;

public class RedisConfigClient extends AConfigCilent{
	
	private RedisTemplate<String,String> redisTemplate;
	
	public RedisConfigClient(AConfigCilent configCilent){
		this.configCilent = configCilent;
		try {
			if(isDo()){
				inited = true;
			}
		} catch (Exception e) {
		}
	}

	@Override
	public String getConfig(String key) {
		if(!isDo()){
			return null;
		}
		if(!init()){
			return null;
		}
		
		if(StringUtils.isBlank(key)){
			return null;
		}
		
		if(key.indexOf(".")!=-1){
			key = key.replace(".", ":");
		}
				
		BoundValueOperations<String,String> boundValueOperations =  redisTemplate.boundValueOps(key);
		if(boundValueOperations!=null){
			Object v =  boundValueOperations.get();
			if(v!=null){
				return v.toString();
			}
		}
		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	private boolean init(){
		try {
			if(redisTemplate==null){
				redisTemplate = (RedisTemplate<String,String>) SpringBeanUtil.getBean(RedisTemplate.class);
			}
			return redisTemplate!=null;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	protected String getName() {
		return "redis";
	}
	
}

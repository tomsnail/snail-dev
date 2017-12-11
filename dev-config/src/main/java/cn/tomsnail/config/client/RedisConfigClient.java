package cn.tomsnail.config.client;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import cn.tomsnail.starter.domain.spring.SpringBeanUtil;
import cn.tomsnail.util.configfile.ConfigHelp;
import cn.tomsnail.util.string.StringUtils;

public class RedisConfigClient extends AConfigCilent{
	
	private RedisTemplate redisTemplate;
	
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
		if(redisTemplate==null){
			init();
			if(redisTemplate==null){
				return null;
			}
		}
		
		
		if(StringUtils.isBlank(key)){
			return null;
		}
		
		if(key.indexOf(".")!=-1){
			key = key.replace(".", ":");
		}
		
		BoundValueOperations boundValueOperations =  redisTemplate.boundValueOps(key);
		if(boundValueOperations!=null){
			Object v =  boundValueOperations.get();
			if(v!=null){
				return v.toString();
			}
		}
		
		return null;
	}

	@Override
	protected boolean isDo() {
		String iszkconfig = ConfigHelp.getInstance("config").getLocalConfig("system.redisconfig", "false");
		if(iszkconfig.equals("true")){
			return true;
		}
		return false;
	}
	
	private void init(){
		redisTemplate = (RedisTemplate) SpringBeanUtil.getBean(RedisTemplate.class);
	}

	
	
}

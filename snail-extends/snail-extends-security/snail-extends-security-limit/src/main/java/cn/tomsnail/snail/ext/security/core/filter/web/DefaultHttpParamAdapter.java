package cn.tomsnail.snail.ext.security.core.filter.web;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import cn.tomsnail.snail.ext.security.core.authentication.AuthentToken;
import cn.tomsnail.snail.ext.security.core.authorization.model.TResource;
import cn.tomsnail.snail.ext.security.core.authorization.model.TUser;
import cn.tomsnail.snail.ext.security.core.filter.IParamAdapter;



/**
 *        默认的http参数适配
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午6:29:56
 * @see 
 */
public class DefaultHttpParamAdapter implements IParamAdapter{
	
	private static final ObjectMapper objectMapper = new ObjectMapper();;

	@Override
	public AuthentToken getToken(Map<String,Object> paramMap) {
		AuthentToken token = new AuthentToken();
		token.setFingerprint(paramMap.remove("fingerprint")+"");
		token.setKey(paramMap.get("key")+"");
		token.setAddtionMap(paramMap);
		return token;
	}

	@Override
	public TResource getTResource(Map<String,Object> paramMap) {
		TResource resource = new TResource();
		resource.setCode(paramMap.get("command")+"");
		resource.setType(TResource.URL);
		return resource;
	}

	@Override
	public TUser getTUser(Map<String,Object> paramMap) {
		TUser user = new TUser();
		user.setUuid(paramMap.get("key")+"");
		return user;
	}

	@Override
	public Map<String, Object> getParamMap(Object obj) {
		String body = (String) obj;
		try {
			Map<String, Object> map = objectMapper.readValue(body, Map.class);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

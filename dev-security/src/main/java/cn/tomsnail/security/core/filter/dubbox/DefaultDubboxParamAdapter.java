package cn.tomsnail.security.core.filter.dubbox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.security.core.authentication.AuthentToken;
import cn.tomsnail.security.core.authorization.model.TResource;
import cn.tomsnail.security.core.authorization.model.TUser;
import cn.tomsnail.security.core.filter.IParamAdapter;

/**
 *        默认dubbox参数适配
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月9日 上午10:33:33
 * @see 
 */
public class DefaultDubboxParamAdapter implements IParamAdapter{

	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public Map<String,Object> getParamMap(Object obj){
		ContainerRequestContext requestContext = (ContainerRequestContext) obj;
		byte[] buffer = null;
		String params = null;
		String url = null;
		try {
			buffer = IOUtils.toByteArray(requestContext.getEntityStream());
			params = new String(buffer,"UTF-8");
			url = requestContext.getUriInfo().getPath();
			requestContext.setEntityStream(new ByteArrayInputStream(buffer));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!org.apache.commons.lang.StringUtils.isBlank(params)){
			try {
				Map<String,Object> map =  mapper.readValue(params,Map.class);
				map.put("URL", url);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}else{
			try {
				Map<String,Object> map = mapper.readValue(mapper.writeValueAsString(new RequestData<Map<String,Object>>()),Map.class);
				map.put("URL", url);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return null;

	}
	
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
	
	public static void main(String[] args) throws Exception {
		String params = "{\"fingerprint\":\"v4\",\"command\":\"v2\",\"sequenceID\":\"v3\",\"version\":\"v1\",\"body\":{\"dir\":\"123\",\"name\":\"324432\"}}";
	}

}

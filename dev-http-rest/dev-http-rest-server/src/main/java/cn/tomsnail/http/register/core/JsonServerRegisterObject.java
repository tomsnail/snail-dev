package cn.tomsnail.http.register.core;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月28日 下午4:14:11
 * @see 
 */
public class JsonServerRegisterObject extends ServerRegisterObject{

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static JsonServerRegisterObject newRegisterObject(String jsonStr){
		try {
			return objectMapper.readValue(jsonStr, JsonServerRegisterObject.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String geRegisterInfo(){
		try {
			return objectMapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

package cn.tomsnail.http.client.register;

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
 * @date 2016年10月28日 下午4:29:07
 * @see 
 */
public class JsonClientRegisterObject extends ClientRegisterObject{

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	
	public static JsonClientRegisterObject newRegisterObject(String jsonStr){
		try {
			return objectMapper.readValue(jsonStr, JsonClientRegisterObject.class);
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

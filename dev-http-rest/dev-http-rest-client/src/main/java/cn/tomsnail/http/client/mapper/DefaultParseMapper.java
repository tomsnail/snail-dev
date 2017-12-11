package cn.tomsnail.http.client.mapper;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import cn.tomsnail.http.client.core.Response;

/**
 *        默认返回值转换，将json字符串转换为对象
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:26:05
 * @see 
 */
@Component
public class DefaultParseMapper implements IParseMapper{
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Override
	public void parse(Response response,Class mapperType) {
		try {
			response.setBody(OBJECT_MAPPER.readValue(response.getOrigStr().toString(), mapperType));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

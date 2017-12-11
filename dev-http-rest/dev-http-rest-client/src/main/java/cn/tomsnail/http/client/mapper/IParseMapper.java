package cn.tomsnail.http.client.mapper;

import cn.tomsnail.http.client.core.Response;

/**
 *        返回值转换接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午2:02:39
 * @see 
 */
public interface IParseMapper {

	public void parse(Response response,Class mapperType);
	
}

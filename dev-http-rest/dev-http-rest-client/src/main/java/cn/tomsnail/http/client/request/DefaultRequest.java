package cn.tomsnail.http.client.request;

import org.springframework.stereotype.Component;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;
import cn.tomsnail.http.client.httpclient.HttpClientFactory;

/**
 *  默认请求      
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午2:14:23
 * @see 
 */
@Component
public class DefaultRequest implements IRequest{
	
	private static HttpClientFactory httpClientFactory = HttpClientFactory.initialize();

	public Response request(Request request) {
		if(request==null||!request.isValid()){
			return null;
		}
		return httpClientFactory.post(request);
	}
	
	
	
}

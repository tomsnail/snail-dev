package cn.tomsnail.http.client.request;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;

/**
 *        请求接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午2:01:41
 * @see 
 */
public interface IRequest<T,K> {

	public Response<K> request(Request<T> request);
	
}

package cn.tomsnail.http.client.service;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;

/**
 *        客户端服务接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午12:02:46
 * @see 
 */
public interface IService<T,R> {

	/**
	 *        RequestData ResultData类型默认服务
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:33:22
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public ResultData service(String serviceName,RequestData requestData);
	
	/**
	 *        自定义服务
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:33:49
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public T service(String serviceName,R r,T t);
	
}

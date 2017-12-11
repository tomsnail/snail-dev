package cn.tomsnail.http.client.invoker;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;

/**
 *        执行器接口，所有执行器均要实现该接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午12:04:18
 * @see 
 */
public interface IInvoker {

	/**
	 *        执行请求
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:24:56
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean invoker(Request request,Response response);
	
}

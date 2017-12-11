package cn.tomsnail.http.client.filter;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;

/**
 *        请求过滤链
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午4:04:26
 * @see 
 */
public interface ChainFilter {

	/**
	 *        过滤
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:21:44
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void doFilter(Request request, Response response) ;
	
}

package cn.tomsnail.http.client.filter;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;

/**
 *        过滤接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午1:44:18
 * @see 
 */
public interface IFilter {

	/**
	 *        过滤
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:23:15
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void doFilter(Request request,Response response,BasicChainFilter chainFilter);
	
}

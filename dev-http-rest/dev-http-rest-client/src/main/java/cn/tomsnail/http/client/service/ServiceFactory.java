package cn.tomsnail.http.client.service;

import java.util.List;

import cn.tomsnail.http.client.filter.IFilter;
import cn.tomsnail.http.client.invoker.IInvoker;
import cn.tomsnail.http.client.mapper.IParseMapper;
import cn.tomsnail.http.client.request.IRequest;

/**
 *        服务工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 下午4:43:38
 * @see 
 */
public interface ServiceFactory {

	/**
	 *        获取服务
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:35:00
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public IService getService(String servcieName,String address) throws Exception;
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月28日 下午5:19:51
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void setFilters(List<IFilter> filters);

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月28日 下午5:19:54
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void setInvokers(List<IInvoker> invokers);

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月28日 下午5:19:57
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void setParseMapper(IParseMapper parseMapper);
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月28日 下午5:19:59
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void setRequest(IRequest request);
}

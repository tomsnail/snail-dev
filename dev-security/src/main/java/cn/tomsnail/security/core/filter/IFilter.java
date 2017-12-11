package cn.tomsnail.security.core.filter;

/**
 *        过滤器接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午6:30:36
 * @see 
 */
public interface IFilter {

	/**
	 *        过滤
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:30:43
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean doFilter(Object obj);
	
}

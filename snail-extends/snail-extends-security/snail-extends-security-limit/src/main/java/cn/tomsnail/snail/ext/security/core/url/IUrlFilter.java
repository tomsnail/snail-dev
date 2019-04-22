package cn.tomsnail.snail.ext.security.core.url;

/**
 *        url过滤
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月9日 下午3:21:49
 * @see 
 */
public interface IUrlFilter {

	/**
	 *        是否合法
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:32:40
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean isPass(String url);
	
}

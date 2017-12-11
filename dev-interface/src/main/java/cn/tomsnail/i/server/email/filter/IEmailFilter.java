package cn.tomsnail.i.server.email.filter;

import cn.tomsnail.i.core.DataContentPckt;

/**
 *        email过滤
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:49:43
 * @see 
 */
public interface IEmailFilter {

	/**
	 *        过滤
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:49:53
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean filter(DataContentPckt dataContentPckt);
	
}

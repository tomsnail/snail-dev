package cn.tomsnail.cache.core;

import cn.tomsnail.cache.exception.CacheException;

/**
 *        清理缓存接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 下午1:52:27
 * @see 
 */
public interface ICleanCache {

	/**
	 *        清理
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:41:55
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void clean() throws CacheException;
	
}

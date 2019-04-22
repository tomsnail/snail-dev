package cn.tomsnail.snail.ext.cache.core;

/**
 *        缓存工厂接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 上午11:33:22
 * @see 
 */
public interface ICacheFactory {

	/**
	 *        获取缓存模型
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:44:29
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public ICache getCache();
	
	/**
	 *        获取指定配置的缓存模型
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午2:44:39
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public ICache getCache(CacheConfig cacheConfig);
	
}

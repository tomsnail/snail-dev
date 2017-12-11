package cn.tomsnail.cache.core;

/**
 *        缓存类型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午2:36:57
 * @see 
 */
public enum CacheType {

	/**
	 * 本地，map缓存
	 */
	LOCAL,
	/**
	 * redis缓存
	 */
	REDIS,
	/**
	 * ehcache缓存
	 */
	EHCACHE,
	/**
	 * J2CACHE缓存
	 */
	J2CACHE
	
}

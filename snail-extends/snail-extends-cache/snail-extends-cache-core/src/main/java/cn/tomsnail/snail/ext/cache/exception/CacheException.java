package cn.tomsnail.snail.ext.cache.exception;

/**
 *        缓存异常类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 上午11:41:54
 * @see 
 */
public class CacheException extends Exception{

	private static final long serialVersionUID = -8960207058596193933L;

	public CacheException(String error){
		super(error);
	}
	
}

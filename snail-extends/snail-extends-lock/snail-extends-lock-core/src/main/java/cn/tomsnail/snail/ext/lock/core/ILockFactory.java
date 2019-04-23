package cn.tomsnail.snail.ext.lock.core;

/**
 *        锁工厂接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月19日 下午1:25:50
 * @see 
 */
public interface ILockFactory {

	/**
	 *        获取锁
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:38:00
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public ILock getLock();
	
	/**
	 *        获取锁
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:38:05
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public ILock getLock(LockConfig lockConfig);
	
}

package cn.tomsnail.lock.core;

/**
 *        锁接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月19日 下午1:23:41
 * @see 
 */
public interface ILock {

	/**
	 *        锁
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:36:36
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void lock();
	
	/**
	 *        试锁
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:36:39
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean tryLock();
	
	/**
	 *        超时试锁
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:36:44
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean tryLock(long timeout);
	
	/**
	 *        解锁
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:36:48
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean unLock();
	
	/**
	 *        销毁
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:36:51
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void destory();
	
}

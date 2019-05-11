package cn.tomsnail.snail.ext.lock.zookeeper;

import java.util.concurrent.TimeUnit;

import cn.tomsnail.snail.ext.lock.core.ILock;
import cn.tomsnail.snail.ext.lock.core.LockConfig;



/**
 *        zookeeper锁
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月19日 下午1:32:33
 * @see 
 */
public class ZooKeeperLock implements ILock{
	
	private ZKDistributedLock _lock;
	
	public ZooKeeperLock(LockConfig lockConfig){
		_lock = new ZKDistributedLock(lockConfig.getUrl(), lockConfig.getLockName());
	}

	@Override
	public void lock() {
		_lock.lock();
	}

	@Override
	public boolean tryLock() {
		return _lock.tryLock();
	}

	@Override
	public boolean tryLock(long timeout) {
		return _lock.tryLock(timeout, TimeUnit.MILLISECONDS);
	}

	@Override
	public boolean unLock() {
		try {
			_lock.unlock();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void destory() {
		if(_lock!=null)
			_lock = null;
	}

}

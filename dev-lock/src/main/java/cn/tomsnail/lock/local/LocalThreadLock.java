package cn.tomsnail.lock.local;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.tomsnail.lock.core.ILock;

/**
 *        本地线程锁
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月19日 下午1:33:19
 * @see 
 */
public class LocalThreadLock implements ILock{
	
	private Lock lock = new ReentrantLock();

	@Override
	public void lock() {
		lock.lock();
	}

	@Override
	public boolean tryLock() {
		return lock.tryLock();
	}

	@Override
	public boolean tryLock(long timeout) {
		try {
			return lock.tryLock(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean unLock() {
		try {
			lock.unlock();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public void destory() {
		if(lock!=null){
			lock = null;
		}
	}

}

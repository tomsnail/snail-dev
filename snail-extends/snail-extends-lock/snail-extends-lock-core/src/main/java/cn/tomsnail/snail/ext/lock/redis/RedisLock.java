package cn.tomsnail.snail.ext.lock.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import cn.tomsnail.snail.ext.lock.core.ILock;
import cn.tomsnail.snail.ext.lock.core.LockConfig;
import redis.clients.jedis.Jedis;


/**
 *        redis锁实现
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月19日 下午2:41:45
 * @see 
 */
public class RedisLock implements ILock{

	private Lock lock = null;
	
	public RedisLock(LockConfig lockConfig,Jedis jedis){
		lock = new RedisDistributedLock(lockConfig,jedis);
	}
	
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
			lock.tryLock(timeout, TimeUnit.MILLISECONDS);
			return true;
		} catch (Exception e) {
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
		lock = null;
	}

}

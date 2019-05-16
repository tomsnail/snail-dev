package cn.tomsnail.snail.ext.lock.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import cn.tomsnail.snail.ext.lock.core.LockConfig;
import redis.clients.jedis.Jedis;



/**
 *        redis分布式锁
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:39:58
 * @see 
 */
public class RedisDistributedLock implements Lock{
		
	private Jedis jedis;
	
	private long timeout;
	
	private String lockName;
	
	private long expireTime=5000l;

	
	public RedisDistributedLock(LockConfig lockConfig,Jedis jedis){
		this.jedis = jedis;
		timeout = lockConfig.getTimeout();
		lockName = lockConfig.getLockName();
	}
	

	@Override
	public void lock() {
		if(tryLock()){
			return;
		}else{
			waitForLock(timeout);
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		
	}

	@Override
	public boolean tryLock() {
		if (jedis.setnx(lockName, (System.currentTimeMillis()+expireTime)+"") == 1) {
            return true;
        }
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		try {
			if (this.tryLock()) {
				return true;
			}
			return waitForLock(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void unlock() {
		 long current = System.currentTimeMillis();       
		 // 避免删除非自己获取得到的锁
		 if (current < Long.parseLong(jedis.get(lockName))){
		       jedis.del(lockName);
		 }
	}

	@Override
	public Condition newCondition() {
		return null;
	}
	
	private boolean waitForLock(long timeout){
		long time = timeout;
		while(time>=0){
			String currentValueStr = jedis.get(lockName); //redis里的时间
            if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                String oldValueStr = jedis.getSet(lockName, (System.currentTimeMillis()+expireTime)+"");
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    return true;
                }
            }
            time -= 100;
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}

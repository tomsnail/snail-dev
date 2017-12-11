package cn.tomsnail.lock.core;

import cn.tomsnail.lock.local.LocalThreadLock;
import cn.tomsnail.lock.redis.RedisLock;
import cn.tomsnail.lock.zookeeper.ZooKeeperLock;
import cn.tomsnail.redis.core.RedisConfig;
import cn.tomsnail.redis.core.RedisFactory;

/**
 *        默认锁工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月19日 下午1:25:27
 * @see 
 */
public class LockFactory implements ILockFactory,InitFactory{
	
	private ILock lock;
	
	private RedisFactory redisFactory = new RedisFactory();
	
	@Override
	public ILock getLock() {
		if(lock!=null){
			return lock;
		}
		return new LocalThreadLock();
	}

	@Override
	public ILock getLock(LockConfig lockConfig) {
		ILock lock = null;
		if(lockConfig.getLockTarget().compareTo(LockTarget.REIDS)==0){
			RedisConfig redisConfig = new RedisConfig();
			redisConfig.setUrl(lockConfig.getUrl());
			lockConfig.setPassword(lockConfig.getPassword());
			lock = new RedisLock(lockConfig,redisFactory.getJedis(redisConfig));
		}
		if(lockConfig.getLockTarget().compareTo(LockTarget.ZOOKEEPER)==0){
			lock = new ZooKeeperLock(lockConfig);
		}
		if(lockConfig.getLockTarget().compareTo(LockTarget.LOCAL)==0){
			lock = new LocalThreadLock();
		}
		return lock;
	}

	@Override
	public void init() {
		
	}

	public void setLock(ILock lock) {
		this.lock = lock;
	}

	
}

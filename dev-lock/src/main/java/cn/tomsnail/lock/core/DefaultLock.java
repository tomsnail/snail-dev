package cn.tomsnail.lock.core;

/**
 *        默认锁实现
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午4:28:36
 * @see 
 */
public class DefaultLock implements ILock{
	
	private ILock lock;
	
	private LockFactory lockFactory = new LockFactory();
	
	private String url;
	
	private String username;
	
	private String password;
	
	private String lockName;
	
	private LockTarget lockTarget = LockTarget.ZOOKEEPER;
	
	private long timeout = 1000l;
	

	public LockFactory getLockFactory() {
		return lockFactory;
	}

	public void setLockFactory(LockFactory lockFactory) {
		this.lockFactory = lockFactory;
	}
	
	
	
	public ILock getLock() {
		return lock;
	}

	public void setLock(ILock lock) {
		this.lock = lock;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	public LockTarget getLockTarget() {
		return lockTarget;
	}

	public void setLockTarget(LockTarget lockTarget) {
		this.lockTarget = lockTarget;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public void init(){
		LockConfig lockConfig = new LockConfig();
		lockConfig.setLockName(lockName);
		lockConfig.setLockTarget(lockTarget);
		lockConfig.setPassword(password);
		lockConfig.setTimeout(timeout);
		lockConfig.setUrl(url);
		lockConfig.setUsername(username);
		lock = lockFactory.getLock(lockConfig);
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
		return lock.tryLock(timeout);
	}

	@Override
	public boolean unLock() {
		return lock.unLock();
	}

	@Override
	public void destory() {
		lock.destory();
	}

}

package cn.tomsnail.lock.core;

/**
 *        锁模型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月19日 下午1:26:56
 * @see 
 */
public class LockConfig {

	private String url;
	
	private String username;
	
	private String password;
	
	private String lockName;
	
	private LockTarget lockTarget = LockTarget.ZOOKEEPER;
	
	private long timeout = 1000l;
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LockTarget getLockTarget() {
		return lockTarget;
	}

	public void setLockTarget(LockTarget lockTarget) {
		this.lockTarget = lockTarget;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
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
	
	
	
	
}

package cn.tomsnail.redis.core;

/**
 *        redis配置模型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午1:42:50
 * @see 
 */
public class RedisConfig {

	private String url;
	
	private String password= "";
	
	private int maxIdle=100;
	
	private int maxWaitMillis=1000;
	
	private int maxTotal=100;
	
	private boolean testOnBorrow=true;
	
	private int port=6379;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(int maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	
}

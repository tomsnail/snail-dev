package cn.tomsnail.snail.ext.cache.core;

/**
 *        缓存配置模型类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 上午11:33:54
 * @see 
 */
public class CacheConfig {

	/**
	 * url链接
	 */
	private String url;
	
	/**
	 * 端口
	 */
	private int port;
	
	/**
	 * 缓存类型
	 */
	private CacheType cacheType = CacheType.LOCAL;
	
	/**
	 * 缓存名
	 */
	private String name;
	
	/**
	 * 过期时间
	 */
	private long expire;
	
	/**
	 * 超时时间
	 */
	private long timeout;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public CacheType getCacheType() {
		return cacheType;
	}

	public void setCacheType(CacheType cacheType) {
		this.cacheType = cacheType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
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

package cn.tomsnail.security.core.authentication;

import java.util.Map;

/**
 *        验证token
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午1:53:15
 * @see 
 */
public class AuthentToken {

	/**
	 * token
	 */
	private String token;
	
	/**
	 * key
	 */
	private String key;
	
	private long expire;
	
	private long startTime;
	
	/**
	 * 指纹
	 */
	private String fingerprint;
	
	/**
	 * 附加信息
	 */
	private Map<String,Object> addtionMap;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Map<String, Object> getAddtionMap() {
		return addtionMap;
	}

	public void setAddtionMap(Map<String, Object> addtionMap) {
		this.addtionMap = addtionMap;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	
}

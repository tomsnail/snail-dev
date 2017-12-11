package cn.tomsnail.security.core.authentication;

/**
 *        Key
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午2:00:31
 * @see 
 */
public class Key {

	/**
	 * 私钥
	 */
	private String priKey;
	
	/**
	 * 公钥
	 */
	private String pubKey;
	
	/**
	 * 超时
	 */
	private long expire = 1800l*2;
	
	/**
	 * key值
	 */
	private String key = "20160809";

	public String getPriKey() {
		return priKey;
	}

	public void setPriKey(String priKey) {
		this.priKey = priKey;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
}

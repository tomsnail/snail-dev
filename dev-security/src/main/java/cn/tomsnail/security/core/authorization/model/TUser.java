package cn.tomsnail.security.core.authorization.model;

/**
 *        用户
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午5:05:08
 * @see 
 */
public class TUser {

	private String uuid;
	
	private String username;
	
	private String password;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

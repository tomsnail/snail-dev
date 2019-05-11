package cn.tomsnail.snail.ext.mq.core.mq.activemq;

/**
 *        JMS配置
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年1月19日 下午1:27:37
 * @see 
 */
public class JmsSenderConfig {
	
	private String url;
	
	private String subject;
	
	private String username;
	
	private String password;
	
	private boolean topic = false;
	
	private boolean transacted = true;
	
	private boolean persistent = true;
	
	private int coreSenderNumber = 1;
	
	private int maxSenderNumber = 1;
	
	private int localCacheNumber = 100;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean isTopic() {
		return topic;
	}

	public void setTopic(boolean topic) {
		this.topic = topic;
	}

	public boolean isTransacted() {
		return transacted;
	}

	public void setTransacted(boolean transacted) {
		this.transacted = transacted;
	}

	public boolean isPersistent() {
		return persistent;
	}

	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	public int getCoreSenderNumber() {
		return coreSenderNumber;
	}

	public void setCoreSenderNumber(int coreSenderNumber) {
		this.coreSenderNumber = coreSenderNumber;
	}

	public int getMaxSenderNumber() {
		return maxSenderNumber;
	}

	public void setMaxSenderNumber(int maxSenderNumber) {
		this.maxSenderNumber = maxSenderNumber;
	}

	public int getLocalCacheNumber() {
		return localCacheNumber;
	}

	public void setLocalCacheNumber(int localCacheNumber) {
		this.localCacheNumber = localCacheNumber;
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

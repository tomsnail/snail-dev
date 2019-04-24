package cn.tomsnail.snail.ext.mq.core.mq.activemq;

/**
 *        接收配置
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年1月19日 下午2:10:18
 * @see 
 */
public class JmsReceiverConfig {
	
	private String url;
	
	private String subject;
	
	private String username;
	
	private String password;
	
	private boolean topic = false;
	
	private boolean transacted = true;
	
	private boolean persistent = true;
	
	private int coreNumber = 1;
	
	private int maxNumber = 1;
	
	private int localCacheNumber = 100;
	
	private String consumerName;

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

	

	public int getCoreNumber() {
		return coreNumber;
	}

	public void setCoreNumber(int coreNumber) {
		this.coreNumber = coreNumber;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public int getLocalCacheNumber() {
		return localCacheNumber;
	}

	public void setLocalCacheNumber(int localCacheNumber) {
		this.localCacheNumber = localCacheNumber;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	
	
}

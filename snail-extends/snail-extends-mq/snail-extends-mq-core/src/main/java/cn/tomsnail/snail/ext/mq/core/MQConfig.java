package cn.tomsnail.snail.ext.mq.core;


/**
 *        mq配置模型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月17日 下午3:05:52
 * @see 
 */
public class MQConfig {

	/**
	 * 队列名
	 */
	private String name;
	
	/**
	 * 是否是发布订阅模式
	 */
	private boolean isTopic;
	
	/**
	 * jms类型
	 */
	private JmsType jmsType;
	
	/**
	 * 地址
	 */
	private String url;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 组，用于kafka
	 */
	private String group;
	
	/**
	 * 线程数量
	 */
	private int threadNum;
	
	private String factoryClass;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTopic() {
		return isTopic;
	}

	public void setTopic(boolean isTopic) {
		this.isTopic = isTopic;
	}

	public JmsType getJmsType() {
		return jmsType;
	}

	public void setJmsType(JmsType jmsType) {
		this.jmsType = jmsType;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public String getFactoryClass() {
		return factoryClass;
	}

	public void setFactoryClass(String factoryClass) {
		this.factoryClass = factoryClass;
	}
	
	

}

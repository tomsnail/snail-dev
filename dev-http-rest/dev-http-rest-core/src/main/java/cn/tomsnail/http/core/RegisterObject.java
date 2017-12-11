package cn.tomsnail.http.core;


/**
 *        注册对象
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月5日 下午2:13:31
 * @see 
 */
public class RegisterObject {
	
	/**
	 * 服务名
	 */
	private String service;

	/**
	 * 应用、模块名称
	 */
	private String appName;
	
	/**
	 * 地址路径
	 */
	private String addUrl;
	
	/**
	 * 逻辑聚合组，默认为default组
	 */
	private String group;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 权重
	 */
	private int weight;
	
	/**
	 * 失败策略
	 */
	private String failType;
	
	/**
	 * 重试次数
	 */
	private int retryCount;
	
	/**
	 * 负载策略
	 */
	private String lbType;
	
	/**
	 * 超时
	 */
	private int timeout;
	
	
	
	/**
	 * 版本
	 */
	private String version;
	
	

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAddUrl() {
		return addUrl;
	}

	public void setAddUrl(String addUrl) {
		this.addUrl = addUrl;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getFailType() {
		return failType;
	}

	public void setFailType(String failType) {
		this.failType = failType;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public String getLbType() {
		return lbType;
	}

	public void setLbType(String lbType) {
		this.lbType = lbType;
	}
	
	

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String geRegisterInfo(){
		return "";
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
	
}

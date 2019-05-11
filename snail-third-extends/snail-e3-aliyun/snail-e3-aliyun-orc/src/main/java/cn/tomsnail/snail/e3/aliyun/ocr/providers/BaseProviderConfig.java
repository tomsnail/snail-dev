package cn.tomsnail.snail.e3.aliyun.ocr.providers;

public class BaseProviderConfig {
	
	protected String callUrl;
	
	protected String domain;

	protected String appKey;
	
	protected String appSecret;
	
	protected String scheme;
	
	protected int scerType;

	public String getCallUrl() {
		return callUrl;
	}

	public void setCallUrl(String callUrl) {
		this.callUrl = callUrl;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public int getScerType() {
		return scerType;
	}

	public void setScerType(int scerType) {
		this.scerType = scerType;
	}

	public BaseProviderConfig(String callUrl, String domain, String appKey,String appSecret, String scheme, int scerType) {
		super();
		this.callUrl = callUrl;
		this.domain = domain;
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.scheme = scheme;
		this.scerType = scerType;
	}

	public BaseProviderConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static BaseProviderConfig build(String callUrl, String domain, String appKey,String appSecret, String scheme, int scerType) {
		return new BaseProviderConfig(callUrl, domain, appKey, appSecret, scheme, scerType);
	}
	
	public static BaseProviderConfig build(String callUrl, String domain, String appKey,String appSecret, String scheme) {
		return new BaseProviderConfig(callUrl, domain, appKey, appSecret, scheme, 0);
	}
	
	public static BaseProviderConfig buildHttps(String callUrl, String domain, String appKey,String appSecret) {
		return new BaseProviderConfig(callUrl, domain, appKey, appSecret, "https", 0);
	}
	
	public static BaseProviderConfig buildHttp(String callUrl, String domain, String appKey,String appSecret) {
		return new BaseProviderConfig(callUrl, domain, appKey, appSecret, "http", 0);
	}
	
	

}

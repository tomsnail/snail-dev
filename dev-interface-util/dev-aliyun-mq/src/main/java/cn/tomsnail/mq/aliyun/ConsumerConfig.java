package cn.tomsnail.mq.aliyun;

import cn.tomsnail.framwork.encrypt.MD5Util;

public class ConsumerConfig {

	private String consumerId;
	
	private String accessKey;
	
	private String secretKey;
	
	private String oNSAddr;
	
	private String md5;

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getoNSAddr() {
		return oNSAddr;
	}

	public void setoNSAddr(String oNSAddr) {
		this.oNSAddr = oNSAddr;
	}

	public ConsumerConfig(String producerId, String accessKey, String secretKey,
			String oNSAddr) {
		super();
		this.consumerId = producerId;
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.oNSAddr = oNSAddr;
		try {
			md5 = MD5Util.md5Encode(this.consumerId+this.accessKey+this.secretKey+this.oNSAddr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMd5(){
		return md5;
	}
	
	
	
}

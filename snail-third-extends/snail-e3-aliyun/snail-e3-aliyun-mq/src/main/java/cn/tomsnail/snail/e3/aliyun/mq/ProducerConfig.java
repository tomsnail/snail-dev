package cn.tomsnail.snail.e3.aliyun.mq;

import cn.tomsnail.snail.core.util.math.encrypt.MD5Util;

public class ProducerConfig {

	private String producerId;
	
	private String accessKey;
	
	private String secretKey;
	
	private String oNSAddr;
	
	private String md5;

	public String getProducerId() {
		return producerId;
	}

	public void setProducerId(String producerId) {
		this.producerId = producerId;
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

	public ProducerConfig(String producerId, String accessKey, String secretKey,
			String oNSAddr) {
		super();
		this.producerId = producerId;
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.oNSAddr = oNSAddr;
		try {
			md5 = MD5Util.md5Encode(this.producerId+this.accessKey+this.secretKey+this.oNSAddr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMd5(){
		return md5;
	}
	
	
	
}

package cn.tomsnail.weixin.util.payment;

import cn.tomsnail.weixin.util.Constants;
import cn.tomsnail.weixin.util.common.SecUtil;

public class ShortURL {

	private String appid = Constants.WXAPPID;
	
	private String mch_id = Constants.MCH_ID;
	
	private String long_url;
	
	private String nonce_str = SecUtil.getNonceStr();
	
	private String sign;
	
	private String sing_type;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getLong_url() {
		return long_url;
	}

	public void setLong_url(String long_url) {
		this.long_url = long_url;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSing_type() {
		return sing_type;
	}

	public void setSing_type(String sing_type) {
		this.sing_type = sing_type;
	}
	
	
	
}

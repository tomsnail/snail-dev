package cn.tomsnail.snail.e3.tx.weixin.util.payment;

import cn.tomsnail.snail.e3.tx.weixin.util.Constants;
import cn.tomsnail.snail.e3.tx.weixin.util.common.SecUtil;

public class SearchOrder {

	/**
	 * 必填
	 */
	private String appid = Constants.WXAPPID;
	/**
	 * 必填
	 */
	private String mch_id = Constants.MCH_ID;
	/**
	 * 微信订单号，同商户订单号二选一 必填
	 */
	private String transaction_id;
	/**
	 * 商户订单号，同微信订单号二选一 必填
	 */
	private String out_trade_no;
	/**
	 * 必填
	 */
	private String nonce_str = SecUtil.getNonceStr();
	/**
	 * 必填
	 */
	private String sign;
	/**
	 * 签名类型
	 */
	private String sign_type;

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

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
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

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
	
	
}

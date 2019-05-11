package cn.tomsnail.snail.e3.tx.weixin.util.redpackets;

import cn.tomsnail.snail.e3.tx.weixin.util.Constants;
import cn.tomsnail.snail.e3.tx.weixin.util.common.SecUtil;

/**
 * 红包
 */
public class RedPackets {

	 /**
	  * 随机字符串，32位，必填
	  */
	 private String nonce_str= SecUtil.getNonceStr();
	 /**
	  * 签名：MD5，32位，必填
	  */
	 private String sign;
	 /**
	  * 商户订单号：28位，mch_id+yyyymmdd+10位，必填
	  */
	 private String mch_billno;
	 /**
	  * 商户号，必填
	  */
	 private String mch_id = Constants.MCH_ID;
	 /**
	  * 公众账户appid，必填
	  */
	 private String wxappid = Constants.WXAPPID;
	 /**
	  * 商户名称，必填
	  */
	 private String send_name = Constants.MCH_NAME;
	 /**
	  * 用户openid，必填
	  */
	 private String re_openid;
	 /**
	  * 付款金额，单位分，必填
	  */
	 private int total_amount = 0;
	 /**
	  * 发送人数，必填
	  */
	 private int total_num = 1;
	 /**
	  * 红包祝福语，必填
	  */
	 private String wishing;
	 /**
	  * ip地址，调用接口的机器Ip地址，必填
	  */
	 private String client_ip;
	 /**
	  * 活动名称，必填
	  */
	 private String act_name;
	 /**
	  * 备注
	  */
	 private String remark;
	 /**
	  * 场景ID：发放红包使用场景，红包金额大于200时必传
				PRODUCT_1:商品促销
				PRODUCT_2:抽奖
				PRODUCT_3:虚拟物品兑奖 
				PRODUCT_4:企业内部福利
				PRODUCT_5:渠道分润
				PRODUCT_6:保险回馈
				PRODUCT_7:彩票派奖
				PRODUCT_8:税务刮奖
	  */
	 private String scene_id = "PRODUCT_1";
	 /**
	  * 活动信息
	  */
	 private String risk_info;
	 /**
	  * 签名：MD5
	  */
	 private String consume_mch_id;
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
	public String getMch_billno() {
		return mch_billno;
	}
	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getWxappid() {
		return wxappid;
	}
	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public int getTotal_num() {
		return total_num;
	}
	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}
	public String getWishing() {
		return wishing;
	}
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getScene_id() {
		return scene_id;
	}
	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}
	public String getRisk_info() {
		return risk_info;
	}
	public void setRisk_info(String risk_info) {
		this.risk_info = risk_info;
	}
	public String getConsume_mch_id() {
		return consume_mch_id;
	}
	public void setConsume_mch_id(String consume_mch_id) {
		this.consume_mch_id = consume_mch_id;
	}
	
	 
	
}

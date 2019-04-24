package cn.tomsnail.snail.e3.tx.weixin.util.payment;

import cn.tomsnail.snail.e3.tx.weixin.util.common.SecUtil;

public class UnifiedOrder {
	
	/**
	 * 公众号id，必填
	 */
	private String appid;
	/**
	 * 商户号id，必填
	 */
	private String mch_id;
	/**
	 * 设备号
	 */
	private String device_info;
	/**
	 * 随机数，必填
	 */
	private String nonce_str = SecUtil.getNonceStr();
	/**
	 * 签名，必填
	 */
	private String sign;
	/**
	 * 签名类型 MD5/HMAC-SHA256,默认MD5
	 */
	private String sign_type;
	/**
	 * 商品信息：浏览器打开的移动网页的主页title名-商品概述，必填
	 */
	private String body;
	/**
	 * 商品详情
	 */
	private GoodsDetail detail;
	/**
	 * 附加数据
	 */
	private String attach;
	/**
	 * 商户订单号，必填
	 */
	private String out_trade_no;
	/**
	 * 币种 ，默认CNY
	 */
	private String fee_type;
	/**
	 * 金额，分，必填
	 */
	private int total_fee;
	/**
	 * 终端IP，APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP 必填 
	 */
	private String spbill_create_ip;
	/**
	 * 交易起始时间
	 */
	private String time_start;
	/**
	 * 交易结束时间
	 */
	private String time_expire;
	/**
	 * 商品标记
	 */
	private String goods_tag;
	/**
	 * 通知地址，异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数 必填
	 */
	private String notify_url;
	/**
	 * 交易类型，JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，必填
	 */
	private String trade_type;
	/**
	 * 商品ID
	 */
	private String product_id;
	/**
	 * 指定支付方式，no_credit，不能使用信用卡支付
	 */
	private String limit_pay;
	/**
	 * 用户id，trade_type=JSAPI时（即公众号支付），此参数必传
	 */
	private String openid;

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

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public GoodsDetail getDetail() {
		return detail;
	}

	public void setDetail(GoodsDetail detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
	
	

}

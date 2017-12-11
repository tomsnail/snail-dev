package cn.tomsnail.weixin.util.payment;

import cn.tomsnail.weixin.util.common.SecUtil;

public class PaymentResult {
	
	private String return_code;
	
	private String return_msg;
	
	private String result_code;
	
	private String err_code;
	
	private String err_code_des;
	
	/**
	 * 是否关注
	 */
	private String is_subscribe;
	
	/**
	 * 交易状态
	 * SUCCESS—支付成功
	 * REFUND—转入退款
	 * NOTPAY—未支付
	 * CLOSED—已关闭
	 * REVOKED—已撤销（刷卡支付）
	 * USERPAYING--用户支付中
	 * PAYERROR--支付失败(其他原因，如银行返回失败)
	 */
	private String trade_state;
	/**
	 * 银行
	 */
	private String bank_type;
	/**
	 * 结算金额 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额
	 */
	private String settlement_total_fee;
	/**
	 * 现金金额
	 */
	private String cash_fee;
	/**
	 * 现金类型
	 */
	private String cash_fee_type;
	/**
	 * 代金券金额
	 */
	private String coupon_fee;
	/**
	 * 代金券数量
	 */
	private String coupon_count;
	/**
	 * 微信订单号
	 */
	private String transaction_id;
	/**
	 * 交易结束时间
	 */
	private String time_end;
	/**
	 * 交易状态描述
	 */
	private String trade_state_desc;
	/**
	 * 预支付交易会话标识,微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	 */
	private String prepay_id;
	/**
	 * 二维码链接,trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
	 */
	private String code_url;
	
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
	private String total_fee;
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

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
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

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public String getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	public String getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(String coupon_count) {
		this.coupon_count = coupon_count;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getTrade_state_desc() {
		return trade_state_desc;
	}

	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
	
	
	
	

}

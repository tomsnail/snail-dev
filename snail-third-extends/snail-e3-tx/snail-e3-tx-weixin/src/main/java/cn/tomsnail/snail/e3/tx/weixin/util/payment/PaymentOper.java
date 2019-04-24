package cn.tomsnail.snail.e3.tx.weixin.util.payment;

public interface PaymentOper {

	String shorturl = "https://api.mch.weixin.qq.com/tools/shorturl";
	
	String unifiedorder = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	String orderquery = "https://api.mch.weixin.qq.com/pay/orderquery";
	
	public PaymentResult unifiedorder(UnifiedOrder unifiedOrder);
	
	public PaymentResult orderquery(SearchOrder searchOrder);
	
	public ShortURLResult shorturl(ShortURL shortURL);
	
}

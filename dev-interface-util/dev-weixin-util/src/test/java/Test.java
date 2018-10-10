import cn.tomsnail.weixin.util.accout.WXAccount;
import cn.tomsnail.weixin.util.core.DefaultWechatOperImpl;
import cn.tomsnail.weixin.util.core.WechatOper;
import cn.tomsnail.weixin.util.qrc.QRTicket;


public class Test {
	
	private static WechatOper wechatOper = new DefaultWechatOperImpl(new WXAccount());

	public static void testqrc(){
		QRTicket qrTicket = wechatOper.createQR("134", 0);
		System.out.println(qrTicket.getTicket()+""+qrTicket.getUrl());
		wechatOper.getQR(qrTicket.getTicket());
	}
	
	public static void main(String[] args) {
		testqrc();
		
		//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcd95afbe2b1103ec&redirect_uri=http%3A%2F%2Fwxtest.yzdhxp.com%2Fwx%2Findex.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
	}
	
}

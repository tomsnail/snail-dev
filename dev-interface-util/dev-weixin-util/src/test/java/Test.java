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
	}
	
}

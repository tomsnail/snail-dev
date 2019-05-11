package cn.tomsnail.snail.e3.tx.weixin.util.payment;

import java.util.List;

public class PaymentNotify {

	private List<INotify> notifies;
	
	public void notify(PaymentResult paymentResult){
		if(paymentResult!=null&&notifies!=null){
			for(INotify notify:notifies){
				notify.notify(paymentResult);
			}
		}
	}
	
}

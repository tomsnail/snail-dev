package cn.tomsnail.snail.e3.tx.weixin.util.payment;

import cn.tomsnail.snail.core.util.commons.CollectionUtils;

import java.util.List;

public class PaymentNotify {

	private List<INotify> notifies;
	
	public void notify(PaymentResult paymentResult){
		if(paymentResult!=null&& CollectionUtils.isNotEmpty(notifies)){
			for(INotify notify:notifies){
				notify.notify(paymentResult);
			}
		}
	}

	public List<INotify> getNotifies() {
		return notifies;
	}

	public void setNotifies(List<INotify> notifies) {
		this.notifies = notifies;
	}
}

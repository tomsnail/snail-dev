package cn.tomsnail.framwork.core.page;

import java.io.Serializable;

public class OrderBy implements Serializable{
	
	private String orderName;
	
	private String orderType;

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public OrderBy(String orderName, String orderType) {
		super();
		this.orderName = orderName;
		this.orderType = orderType;
	}
	

	
	
}

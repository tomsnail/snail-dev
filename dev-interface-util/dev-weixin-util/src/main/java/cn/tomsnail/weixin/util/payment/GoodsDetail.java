package cn.tomsnail.weixin.util.payment;

import java.util.List;

public class GoodsDetail {

	private int cost_price;
	
	private int receipt_id;
	
	private List<Goods> goods_detail;

	public int getCost_price() {
		return cost_price;
	}

	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}

	public int getReceipt_id() {
		return receipt_id;
	}

	public void setReceipt_id(int receipt_id) {
		this.receipt_id = receipt_id;
	}

	public List<Goods> getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(List<Goods> goods_detail) {
		this.goods_detail = goods_detail;
	}
	
	
	
	
	
	
}

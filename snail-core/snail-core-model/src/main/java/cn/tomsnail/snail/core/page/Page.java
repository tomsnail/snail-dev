package cn.tomsnail.snail.core.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable{
	
	private int pageNo = 1; // 当前页码
	
	private int pageSize = 10; // 页面大小，设置为“-1”表示不进行分页（分页无效）
	
	public List<OrderBy> orderBys = new ArrayList<OrderBy>();
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<OrderBy> getOrderBys() {
		return orderBys;
	}
	public void setOrderBys(List<OrderBy> orderBys) {
		this.orderBys = orderBys;
	}
	
	public Page addOrderByDesc(String orderByName){
		
		orderBys.add(new OrderBy(orderByName, "desc"));
		
		return this;
	}
	
	public Page addOrderBy(String orderByName){
		
		orderBys.add(new OrderBy(orderByName, "asc"));
		
		return this;
	}

}

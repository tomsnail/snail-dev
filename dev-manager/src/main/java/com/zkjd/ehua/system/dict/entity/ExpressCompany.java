/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.dict.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 快递公司Entity
 * @author yangsong
 * @version 2017-02-28
 */
public class ExpressCompany extends DataEntity<ExpressCompany> {
	
	private static final long serialVersionUID = 1L;
	private String coName;		// 公司名称
	private String status;		// 状态
	private String num;		// 编号
	private String inl;		// 首字母
	private String sortNo;		// 排序
	private String coWebsite;		// 公司网站
	private String iconAddr;		// 图标地址
	
	public ExpressCompany() {
		super();
	}

	public ExpressCompany(String id){
		super(id);
	}

	@Length(min=1, max=200, message="公司名称长度必须介于 1 和 200 之间")
	public String getCoName() {
		return coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}
	
	@Length(min=1, max=12, message="状态长度必须介于 1 和 12 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=200, message="编号长度必须介于 1 和 200 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=1, max=200, message="首字母长度必须介于 1 和 200 之间")
	public String getInl() {
		return inl;
	}

	public void setInl(String inl) {
		this.inl = inl;
	}
	
	@Length(min=1, max=11, message="排序长度必须介于 1 和 11 之间")
	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	
	@Length(min=0, max=200, message="公司网站长度必须介于 0 和 200 之间")
	public String getCoWebsite() {
		return coWebsite;
	}

	public void setCoWebsite(String coWebsite) {
		this.coWebsite = coWebsite;
	}
	
	@Length(min=0, max=200, message="图标地址长度必须介于 0 和 200 之间")
	public String getIconAddr() {
		return iconAddr;
	}

	public void setIconAddr(String iconAddr) {
		this.iconAddr = iconAddr;
	}
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.dict.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 地区Entity
 * @author yangsong
 * @version 2017-02-28
 */
public class RegionInfo extends TreeEntity<RegionInfo> {
	
	private static final long serialVersionUID = 1L;
	private String regionName;		// 地区名称
	private RegionInfo parent;		// 父地区ID
	private String name;		// 名称
	private String parentIds;		// 父节点级数
	private String areaName;		// 大区名称
	private String regionDepth;		// 地区深度
	private String postNum;		// 邮政编号
	private String adminNum;		// 行政编号
	private String sortNo;		// 排序
	
	public RegionInfo() {
		super();
	}

	public RegionInfo(String id){
		super(id);
	}

	@Length(min=1, max=200, message="地区名称长度必须介于 1 和 200 之间")
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	@JsonBackReference
	public RegionInfo getParent() {
		return parent;
	}

	public void setParent(RegionInfo parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=200, message="名称长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2000, message="父节点级数长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=200, message="大区名称长度必须介于 1 和 200 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Length(min=0, max=200, message="地区深度长度必须介于 0 和 200 之间")
	public String getRegionDepth() {
		return regionDepth;
	}

	public void setRegionDepth(String regionDepth) {
		this.regionDepth = regionDepth;
	}
	
	@Length(min=0, max=200, message="邮政编号长度必须介于 0 和 200 之间")
	public String getPostNum() {
		return postNum;
	}

	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	
	@Length(min=0, max=200, message="行政编号长度必须介于 0 和 200 之间")
	public String getAdminNum() {
		return adminNum;
	}

	public void setAdminNum(String adminNum) {
		this.adminNum = adminNum;
	}
	
	@Length(min=1, max=11, message="排序长度必须介于 1 和 11 之间")
	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}
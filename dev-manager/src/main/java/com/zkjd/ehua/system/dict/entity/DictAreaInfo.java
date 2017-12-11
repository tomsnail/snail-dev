/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.dict.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 地区Entity
 * @author yangsong
 * @version 2017-02-28
 */
public class DictAreaInfo extends TreeEntity<DictAreaInfo> {
	
	private static final long serialVersionUID = 1L;
	private String areaName;		// 地区名称
	private DictAreaInfo parent;		// 地区父ID
	private String parentIds;		// 父节点级数
	private String areaSort;		// 排序
	private String areaDeep;		// 地区深度
	private String deptCode;		// 地区编号
	
	public DictAreaInfo() {
		super();
	}

	public DictAreaInfo(String id){
		super(id);
	}

	@Length(min=1, max=200, message="地区名称长度必须介于 1 和 200 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@JsonBackReference
	@NotNull(message="地区父ID不能为空")
	public DictAreaInfo getParent() {
		return parent;
	}

	public void setParent(DictAreaInfo parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="父节点级数长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public String getAreaSort() {
		return areaSort;
	}

	public void setAreaSort(String areaSort) {
		this.areaSort = areaSort;
	}
	
	public String getAreaDeep() {
		return areaDeep;
	}

	public void setAreaDeep(String areaDeep) {
		this.areaDeep = areaDeep;
	}
	
	@Length(min=0, max=50, message="地区编号长度必须介于 0 和 50 之间")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}
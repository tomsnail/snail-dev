/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.config.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 系统配置Entity
 * @author yangsong
 * @version 2017-03-09
 */
public class SysConfig extends DataEntity<SysConfig> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String value;		// 值
	private String type;		// 类型
	private String desc;		// 描述
	private String isUse = Global.YES ;		// 是否启用
	
	public SysConfig() {
		super();
	}

	public SysConfig(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1000, message="值长度必须介于 0 和 1000 之间")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Length(min=0, max=12, message="类型长度必须介于 0 和 12 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Length(min=0, max=2, message="是否启用长度必须介于 0 和 2 之间")
	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	
}
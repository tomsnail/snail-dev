/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.msgtemp.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 消息模板Entity
 * @author yangsong
 * @version 2017-03-25
 */
public class MsgTemplate extends DataEntity<MsgTemplate> {
	
	private static final long serialVersionUID = 1L;
	private String templName;		// 模板名称
	private String templDesc;		// 模板描述
	private String templCode;		// 模板编号
	private String templType;		// 模板类型
	private String templContext;		// 模板内容
	private String templSendType;		// 发送方式
	private String isUse = Global.YES;		// 是否启用
	
	public MsgTemplate() {
		super();
	}

	public MsgTemplate(String id){
		super(id);
	}

	@Length(min=0, max=255, message="模板名称长度必须介于 0 和 255 之间")
	public String getTemplName() {
		return templName;
	}

	public void setTemplName(String templName) {
		this.templName = templName;
	}
	
	@Length(min=0, max=255, message="模板描述长度必须介于 0 和 255 之间")
	public String getTemplDesc() {
		return templDesc;
	}

	public void setTemplDesc(String templDesc) {
		this.templDesc = templDesc;
	}
	
	@Length(min=0, max=255, message="模板编号长度必须介于 0 和 255 之间")
	public String getTemplCode() {
		return templCode;
	}

	public void setTemplCode(String templCode) {
		this.templCode = templCode;
	}
	
	@Length(min=0, max=255, message="模板类型长度必须介于 0 和 255 之间")
	public String getTemplType() {
		return templType;
	}

	public void setTemplType(String templType) {
		this.templType = templType;
	}
	
	@Length(min=0, max=2000, message="模板内容长度必须介于 0 和 2000 之间")
	public String getTemplContext() {
		return templContext;
	}

	public void setTemplContext(String templContext) {
		this.templContext = templContext;
	}
	
	@Length(min=0, max=255, message="发送方式长度必须介于 0 和 255 之间")
	public String getTemplSendType() {
		return templSendType;
	}

	public void setTemplSendType(String templSendType) {
		this.templSendType = templSendType;
	}
	
	@Length(min=0, max=1, message="是否启用长度必须介于 0 和 1 之间")
	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	
}
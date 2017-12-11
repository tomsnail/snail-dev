/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.visit.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 访问控制Entity
 * @author yangsong
 * @version 2017-03-14
 */
public class VisitControl extends DataEntity<VisitControl> {
	
	private static final long serialVersionUID = 1L;
	private String visitIp;		// 访问IP
	private String isLimit = Global.YES;		// 是否限制
	private Date limitTime;
	
	public VisitControl() {
		super();
	}

	public VisitControl(String id){
		super(id);
	}

	@Length(min=0, max=200, message="访问IP长度必须介于 0 和 200 之间")
	public String getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}
	
	@Length(min=0, max=11, message="是否限制长度必须介于 0 和 11 之间")
	public String getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(String isLimit) {
		this.isLimit = isLimit;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}

	
	
	
	
	
}
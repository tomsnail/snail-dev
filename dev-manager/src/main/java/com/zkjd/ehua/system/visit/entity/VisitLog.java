/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.visit.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 访问日志Entity
 * @author yangsong
 * @version 2017-03-14
 */
public class VisitLog extends DataEntity<VisitLog> {
	
	private static final long serialVersionUID = 1L;
	private String visitAddr;		// 访问地址
	private String addrCode;		// 地址编码
	private String visitIp;		// 访问IP
	private String visitUserId;		// 访问用户
	private String visitUserName;		// 用户名称
	private Date visitTime;		// 访问时间
	private String visitContext;		// 访问内容
	private String visitClient;		// 访问客户端
	private Date beginVisitTime;		// 开始 访问时间
	private Date endVisitTime;		// 结束 访问时间
	
	public VisitLog() {
		super();
	}

	public VisitLog(String id){
		super(id);
	}

	@Length(min=0, max=200, message="访问地址长度必须介于 0 和 200 之间")
	public String getVisitAddr() {
		return visitAddr;
	}

	public void setVisitAddr(String visitAddr) {
		this.visitAddr = visitAddr;
	}
	
	@Length(min=0, max=255, message="地址编码长度必须介于 0 和 255 之间")
	public String getAddrCode() {
		return addrCode;
	}

	public void setAddrCode(String addrCode) {
		this.addrCode = addrCode;
	}
	
	@Length(min=0, max=100, message="访问IP长度必须介于 0 和 100 之间")
	public String getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}
	
	@Length(min=0, max=36, message="访问用户长度必须介于 0 和 36 之间")
	public String getVisitUserId() {
		return visitUserId;
	}

	public void setVisitUserId(String visitUserId) {
		this.visitUserId = visitUserId;
	}
	
	@Length(min=0, max=100, message="用户名称长度必须介于 0 和 100 之间")
	public String getVisitUserName() {
		return visitUserName;
	}

	public void setVisitUserName(String visitUserName) {
		this.visitUserName = visitUserName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	
	@Length(min=0, max=1000, message="访问内容长度必须介于 0 和 1000 之间")
	public String getVisitContext() {
		return visitContext;
	}

	public void setVisitContext(String visitContext) {
		this.visitContext = visitContext;
	}
	
	@Length(min=0, max=1000, message="访问客户端长度必须介于 0 和 1000 之间")
	public String getVisitClient() {
		return visitClient;
	}

	public void setVisitClient(String visitClient) {
		this.visitClient = visitClient;
	}
	
	public Date getBeginVisitTime() {
		return beginVisitTime;
	}

	public void setBeginVisitTime(Date beginVisitTime) {
		this.beginVisitTime = beginVisitTime;
	}
	
	public Date getEndVisitTime() {
		return endVisitTime;
	}

	public void setEndVisitTime(Date endVisitTime) {
		this.endVisitTime = endVisitTime;
	}
		
}
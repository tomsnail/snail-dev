/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.msg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 平台消息Entity
 * @author yangsong
 * @version 2017-03-25
 */
public class SystemMessage extends DataEntity<SystemMessage> {
	
	private static final long serialVersionUID = 1L;
	private String msgTitle;		// 消息标题
	private String msgType;		// 消息类型
	private String msgContent;		// 消息内容
	private String msgUrl;		// 链接URL
	private String sendPerson;		// 发送人
	private String receivePerson;		// 接收人
	private Date msgDate;		// 消息时间
	private String status;		// 消息状态
	private String readUser;		// 已读会员
	private String delUser;		// 删除会员
	private String isMsgMulti ;		// 消息是否多播
	private String msgSys;		// 消息系统
	
	public SystemMessage() {
		super();
	}

	public SystemMessage(String id){
		super(id);
	}

	@Length(min=0, max=200, message="消息标题长度必须介于 0 和 200 之间")
	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	
	@Length(min=0, max=30, message="消息类型长度必须介于 0 和 30 之间")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	@Length(min=0, max=2000, message="消息内容长度必须介于 0 和 2000 之间")
	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	@Length(min=0, max=200, message="链接URL长度必须介于 0 和 200 之间")
	public String getMsgUrl() {
		return msgUrl;
	}

	public void setMsgUrl(String msgUrl) {
		this.msgUrl = msgUrl;
	}
	
	@Length(min=0, max=200, message="发送人长度必须介于 0 和 200 之间")
	public String getSendPerson() {
		return sendPerson;
	}

	public void setSendPerson(String sendPerson) {
		this.sendPerson = sendPerson;
	}
	
	@Length(min=0, max=200, message="接收人长度必须介于 0 和 200 之间")
	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}
	
	@Length(min=0, max=12, message="消息状态长度必须介于 0 和 12 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=200, message="已读会员长度必须介于 0 和 200 之间")
	public String getReadUser() {
		return readUser;
	}

	public void setReadUser(String readUser) {
		this.readUser = readUser;
	}
	
	@Length(min=0, max=200, message="删除会员长度必须介于 0 和 200 之间")
	public String getDelUser() {
		return delUser;
	}

	public void setDelUser(String delUser) {
		this.delUser = delUser;
	}
	
	@Length(min=0, max=11, message="消息是否多播长度必须介于 0 和 11 之间")
	public String getIsMsgMulti() {
		return isMsgMulti;
	}

	public void setIsMsgMulti(String isMsgMulti) {
		this.isMsgMulti = isMsgMulti;
	}
	
	@Length(min=0, max=200, message="消息系统长度必须介于 0 和 200 之间")
	public String getMsgSys() {
		return msgSys;
	}

	public void setMsgSys(String msgSys) {
		this.msgSys = msgSys;
	}
	
}
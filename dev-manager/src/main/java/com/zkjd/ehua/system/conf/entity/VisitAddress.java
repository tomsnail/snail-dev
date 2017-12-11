/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.conf.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 访问地址Entity
 * @author yangsong
 * @version 2017-03-03
 */
public class VisitAddress extends DataEntity<VisitAddress> {
	
	private static final long serialVersionUID = 1L;
	private String visitAddr;		// 访问地址
	private String reverseAgncAddr;		// 反向代理地址
	private String grpgName;		// 分组名称
	private String isRelease;		// 是否发布
	private String isSign ;		// 是否验签
	private String isAuth;		// 是否鉴权
	private String isDefVer;		// 是否默认版本
	private String verInfo;		// 版本
	private String isAddUser;		// 是否添加用户信息
	private String isAddVer;
	private String isLogger;
	private String logLevel;
	private String isInner;
	private String realAddress;
	private String testAddress;
	private String comAddress;
	private String author;
	private String isDegrade;
	private String addressType;
	private String degradeContext;
	private String accessLimitType;
	private String accessLimitValue;
	private String httpType;
	
	public VisitAddress() {
		super();
	}

	public VisitAddress(String id){
		super(id);
	}

	@Length(min=1, max=200, message="访问地址长度必须介于 1 和 200 之间")
	public String getVisitAddr() {
		return visitAddr;
	}

	public void setVisitAddr(String visitAddr) {
		this.visitAddr = visitAddr;
	}
	
	@Length(min=1, max=200, message="反向代理地址长度必须介于 1 和 200 之间")
	public String getReverseAgncAddr() {
		return reverseAgncAddr;
	}

	public void setReverseAgncAddr(String reverseAgncAddr) {
		this.reverseAgncAddr = reverseAgncAddr;
	}
	
	@Length(min=1, max=200, message="分组名称长度必须介于 1 和 200 之间")
	public String getGrpgName() {
		return grpgName;
	}

	public void setGrpgName(String grpgName) {
		this.grpgName = grpgName;
	}
	
	@Length(min=1, max=11, message="是否发布长度必须介于 1 和 11 之间")
	public String getIsRelease() {
		return isRelease;
	}

	public void setIsRelease(String isRelease) {
		this.isRelease = isRelease;
	}
	
	@Length(min=0, max=2, message="是否验签长度必须介于 0 和 2 之间")
	public String getIsSign() {
		return isSign;
	}

	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}
	
	@Length(min=0, max=2, message="是否鉴权长度必须介于 0 和 2 之间")
	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}
	
	@Length(min=0, max=2, message="是否默认版本长度必须介于 0 和 2 之间")
	public String getIsDefVer() {
		return isDefVer;
	}

	public void setIsDefVer(String isDefVer) {
		this.isDefVer = isDefVer;
	}
	
	@Length(min=0, max=255, message="版本长度必须介于 0 和 255 之间")
	public String getVerInfo() {
		return verInfo;
	}

	public void setVerInfo(String verInfo) {
		this.verInfo = verInfo;
	}
	
	@Length(min=0, max=2, message="是否添加用户信息长度必须介于 0 和 2 之间")
	public String getIsAddUser() {
		return isAddUser;
	}

	public void setIsAddUser(String isAddUser) {
		this.isAddUser = isAddUser;
	}

	public String getIsLogger() {
		return isLogger;
	}

	public void setIsLogger(String isLogger) {
		this.isLogger = isLogger;
	}

	public String getIsInner() {
		return isInner;
	}

	public void setIsInner(String isInner) {
		this.isInner = isInner;
	}

	public String getRealAddress() {
		return realAddress;
	}

	public void setRealAddress(String realAddress) {
		this.realAddress = realAddress;
	}

	public String getTestAddress() {
		return testAddress;
	}

	public void setTestAddress(String testAddress) {
		this.testAddress = testAddress;
	}

	public String getComAddress() {
		return comAddress;
	}

	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsDegrade() {
		return isDegrade;
	}

	public void setIsDegrade(String isDegrade) {
		this.isDegrade = isDegrade;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getDegradeContext() {
		return degradeContext;
	}

	public void setDegradeContext(String degradeContext) {
		this.degradeContext = degradeContext;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getAccessLimitType() {
		return accessLimitType;
	}

	public void setAccessLimitType(String accessLimitType) {
		this.accessLimitType = accessLimitType;
	}

	public String getAccessLimitValue() {
		return accessLimitValue;
	}

	public void setAccessLimitValue(String accessLimitValue) {
		this.accessLimitValue = accessLimitValue;
	}

	public String getIsAddVer() {
		return isAddVer;
	}

	public void setIsAddVer(String isAddVer) {
		this.isAddVer = isAddVer;
	}

	public String getHttpType() {
		return httpType;
	}

	public void setHttpType(String httpType) {
		this.httpType = httpType;
	}
	
	
}
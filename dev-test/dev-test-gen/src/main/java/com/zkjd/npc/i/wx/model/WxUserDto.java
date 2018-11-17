/**
 * Copyright &copy; 2012-2016
 */
package com.zkjd.npc.i.wx.model;



import cn.tomsnail.dao.plugins.pagination.PageModel;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 *  model
 * @author Wangpeng
 * @version 2018-11-15
 */
public class WxUserDto  extends PageModel<WxUserDto> {
	
	private String id;		// 
	private String uid;		// 
	private String openId;		// 
	private String userId;		// 
	private String companyId;		// 
	private String companyName;		// 
	private int bingStatus;		// 
	private String createBy;		// 
	private Timestamp createDate;		// 
	private String updateBy;		// 
	private Timestamp updateDate;		// 
	private String remarks;		// 
	private int version;		// 
	private String delFlag;		// 
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public int getBingStatus() {
		return bingStatus;
	}

	public void setBingStatus(int bingStatus) {
		this.bingStatus = bingStatus;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}
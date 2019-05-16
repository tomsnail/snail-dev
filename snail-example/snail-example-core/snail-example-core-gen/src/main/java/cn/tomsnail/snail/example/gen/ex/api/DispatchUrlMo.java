/**
 * Copyright &copy; 2012-2016
 */
package cn.tomsnail.snail.example.gen.ex.api;

import java.io.Serializable;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * DispatchUrl model
 * @author Yangsong
 * @version 0.0.1-SNAPSHOT
 */
public class DispatchUrlMo  implements Serializable {
	
	private String id;		 
	private String strategyId;		 
	private String urlType;		 
	private String urlContext;		 
	private String urlUserName;		 
	private String urlPassword;		 
	private String urlAdditional;		 
	private Timestamp createDate;		 
	private String createBy;		 
	private Timestamp updateDate;		 
	private String updateBy;		 
	private String delFlag;		 
	private String remarks;		 
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}
	
	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	
	public String getUrlContext() {
		return urlContext;
	}

	public void setUrlContext(String urlContext) {
		this.urlContext = urlContext;
	}
	
	public String getUrlUserName() {
		return urlUserName;
	}

	public void setUrlUserName(String urlUserName) {
		this.urlUserName = urlUserName;
	}
	
	public String getUrlPassword() {
		return urlPassword;
	}

	public void setUrlPassword(String urlPassword) {
		this.urlPassword = urlPassword;
	}
	
	public String getUrlAdditional() {
		return urlAdditional;
	}

	public void setUrlAdditional(String urlAdditional) {
		this.urlAdditional = urlAdditional;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
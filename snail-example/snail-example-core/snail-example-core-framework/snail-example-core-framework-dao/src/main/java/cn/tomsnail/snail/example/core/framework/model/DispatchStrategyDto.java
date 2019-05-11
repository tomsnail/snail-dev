/**
 * Copyright &copy; 2012-2016
 */
package cn.tomsnail.snail.example.core.framework.model;




import java.io.Serializable;

import cn.tomsnail.snail.core.ds.dao.plugins.pagination.PageModel;

/**
 *  model
 * @author Yangsong
 * @version 2018-09-06
 */
public class DispatchStrategyDto  extends PageModel<DispatchStrategyDto>implements Serializable {
	
	private String id;		// 
	private String strategyCode;		// 
	private String strategyName;		// 
	private String strategyContext;		// 
	private String strategyType;		// 
	private String strategyStatus;		// 
	private String strategyCondition;		// 
	private String createDate;		// 
	private String createBy;		// 
	private String updateDate;		// 
	private String updateBy;		// 
	private String delFlag;		// 
	private String remarks;		// 
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getStrategyCode() {
		return strategyCode;
	}

	public void setStrategyCode(String strategyCode) {
		this.strategyCode = strategyCode;
	}
	
	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	
	public String getStrategyContext() {
		return strategyContext;
	}

	public void setStrategyContext(String strategyContext) {
		this.strategyContext = strategyContext;
	}
	
	public String getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}
	
	public String getStrategyStatus() {
		return strategyStatus;
	}

	public void setStrategyStatus(String strategyStatus) {
		this.strategyStatus = strategyStatus;
	}
	
	public String getStrategyCondition() {
		return strategyCondition;
	}

	public void setStrategyCondition(String strategyCondition) {
		this.strategyCondition = strategyCondition;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
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
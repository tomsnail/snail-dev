/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.flow.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 系统流程Entity
 * @author yangsong
 * @version 2017-03-01
 */
public class SysFlow extends DataEntity<SysFlow> {
	
	private static final long serialVersionUID = 1L;
	private String startFlowId;		// 流程
	private String nextFlowIds;		// 下一个流程
	private String flowDesc;		// 流程描述
	private String flowName;		// 流程名称
	
	public SysFlow() {
		super();
	}

	public SysFlow(String id){
		super(id);
	}

	@Length(min=1, max=36, message="流程长度必须介于 1 和 36 之间")
	public String getStartFlowId() {
		return startFlowId;
	}

	public void setStartFlowId(String startFlowId) {
		this.startFlowId = startFlowId;
	}
	
	@Length(min=0, max=200, message="下一个流程长度必须介于 0 和 200 之间")
	public String getNextFlowIds() {
		return nextFlowIds;
	}

	public void setNextFlowIds(String nextFlowIds) {
		this.nextFlowIds = nextFlowIds;
	}
	
	@Length(min=0, max=255, message="流程描述长度必须介于 0 和 255 之间")
	public String getFlowDesc() {
		return flowDesc;
	}

	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}
	
	@Length(min=1, max=255, message="流程名称长度必须介于 1 和 255 之间")
	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	
}
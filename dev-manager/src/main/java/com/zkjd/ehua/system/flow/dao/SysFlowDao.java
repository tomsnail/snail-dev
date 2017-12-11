/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.flow.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.zkjd.ehua.system.flow.entity.SysFlow;

/**
 * 系统流程DAO接口
 * @author yangsong
 * @version 2017-03-01
 */
@MyBatisDao
public interface SysFlowDao extends CrudDao<SysFlow> {
	
	public List<SysFlow> getFlow(String startFlowId);
	
}
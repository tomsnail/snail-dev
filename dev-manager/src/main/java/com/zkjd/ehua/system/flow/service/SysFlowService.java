/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.flow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.zkjd.ehua.system.flow.entity.SysFlow;
import com.zkjd.ehua.system.flow.dao.SysFlowDao;

/**
 * 系统流程Service
 * @author yangsong
 * @version 2017-03-01
 */
@Service
@Transactional(readOnly = true)
public class SysFlowService extends CrudService<SysFlowDao, SysFlow> {

	public SysFlow get(String id) {
		return super.get(id);
	}
	
	public List<SysFlow> findList(SysFlow sysFlow) {
		return super.findList(sysFlow);
	}
	
	public Page<SysFlow> findPage(Page<SysFlow> page, SysFlow sysFlow) {
		return super.findPage(page, sysFlow);
	}
	
	@Transactional(readOnly = false)
	public void save(SysFlow sysFlow) {
		super.save(sysFlow);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysFlow sysFlow) {
		super.delete(sysFlow);
	}
	
}
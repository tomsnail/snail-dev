/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.zkjd.ehua.system.visit.entity.VisitLog;
import com.zkjd.ehua.system.visit.dao.VisitLogDao;

/**
 * 访问日志Service
 * @author yangsong
 * @version 2017-03-14
 */
@Service
@Transactional(readOnly = true)
public class VisitLogService extends CrudService<VisitLogDao, VisitLog> {

	public VisitLog get(String id) {
		return super.get(id);
	}
	
	public List<VisitLog> findList(VisitLog visitLog) {
		return super.findList(visitLog);
	}
	
	public Page<VisitLog> findPage(Page<VisitLog> page, VisitLog visitLog) {
		return super.findPage(page, visitLog);
	}
	
	@Transactional(readOnly = false)
	public void save(VisitLog visitLog) {
		super.save(visitLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(VisitLog visitLog) {
		super.delete(visitLog);
	}
	
}
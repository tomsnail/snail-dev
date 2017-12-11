/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.zkjd.ehua.system.visit.entity.VisitControl;
import com.zkjd.ehua.system.visit.dao.VisitControlDao;

/**
 * 访问控制Service
 * @author yangsong
 * @version 2017-03-14
 */
@Service
@Transactional(readOnly = true)
public class VisitControlService extends CrudService<VisitControlDao, VisitControl> {

	public VisitControl get(String id) {
		return super.get(id);
	}
	
	public List<VisitControl> findList(VisitControl visitControl) {
		return super.findList(visitControl);
	}
	
	public Page<VisitControl> findPage(Page<VisitControl> page, VisitControl visitControl) {
		return super.findPage(page, visitControl);
	}
	
	@Transactional(readOnly = false)
	public void save(VisitControl visitControl) {
		super.save(visitControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(VisitControl visitControl) {
		super.delete(visitControl);
	}
	
}
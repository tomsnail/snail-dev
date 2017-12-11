/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.dict.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.zkjd.ehua.system.dict.entity.ExpressCompany;
import com.zkjd.ehua.system.dict.dao.ExpressCompanyDao;

/**
 * 快递公司Service
 * @author yangsong
 * @version 2017-02-28
 */
@Service
@Transactional(readOnly = true)
public class ExpressCompanyService extends CrudService<ExpressCompanyDao, ExpressCompany> {

	public ExpressCompany get(String id) {
		return super.get(id);
	}
	
	public List<ExpressCompany> findList(ExpressCompany expressCompany) {
		return super.findList(expressCompany);
	}
	
	public Page<ExpressCompany> findPage(Page<ExpressCompany> page, ExpressCompany expressCompany) {
		return super.findPage(page, expressCompany);
	}
	
	@Transactional(readOnly = false)
	public void save(ExpressCompany expressCompany) {
		super.save(expressCompany);
	}
	
	@Transactional(readOnly = false)
	public void delete(ExpressCompany expressCompany) {
		super.delete(expressCompany);
	}
	
}
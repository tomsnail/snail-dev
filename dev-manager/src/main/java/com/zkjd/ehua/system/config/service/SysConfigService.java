/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.config.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.zkjd.ehua.system.config.entity.SysConfig;
import com.zkjd.ehua.system.config.dao.SysConfigDao;

/**
 * 系统配置Service
 * @author yangsong
 * @version 2017-03-09
 */
@Service
@Transactional(readOnly = true)
public class SysConfigService extends CrudService<SysConfigDao, SysConfig> {

	public SysConfig get(String id) {
		return super.get(id);
	}
	
	public List<SysConfig> findList(SysConfig sysConfig) {
		return super.findList(sysConfig);
	}
	
	public Page<SysConfig> findPage(Page<SysConfig> page, SysConfig sysConfig) {
		return super.findPage(page, sysConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(SysConfig sysConfig) {
		super.save(sysConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysConfig sysConfig) {
		super.delete(sysConfig);
	}
	
}
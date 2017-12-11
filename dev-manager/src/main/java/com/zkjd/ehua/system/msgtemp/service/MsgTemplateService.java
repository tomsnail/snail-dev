/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.msgtemp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.zkjd.ehua.system.msgtemp.entity.MsgTemplate;
import com.zkjd.ehua.system.msgtemp.dao.MsgTemplateDao;

/**
 * 消息模板Service
 * @author yangsong
 * @version 2017-03-25
 */
@Service
@Transactional(readOnly = true)
public class MsgTemplateService extends CrudService<MsgTemplateDao, MsgTemplate> {

	public MsgTemplate get(String id) {
		return super.get(id);
	}
	
	public List<MsgTemplate> findList(MsgTemplate msgTemplate) {
		return super.findList(msgTemplate);
	}
	
	public Page<MsgTemplate> findPage(Page<MsgTemplate> page, MsgTemplate msgTemplate) {
		return super.findPage(page, msgTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(MsgTemplate msgTemplate) {
		super.save(msgTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(MsgTemplate msgTemplate) {
		super.delete(msgTemplate);
	}
	
}
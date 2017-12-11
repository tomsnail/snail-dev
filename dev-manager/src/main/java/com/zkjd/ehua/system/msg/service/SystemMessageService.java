/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.msg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.zkjd.ehua.system.msg.entity.SystemMessage;
import com.zkjd.ehua.system.msg.dao.SystemMessageDao;

/**
 * 平台消息Service
 * @author yangsong
 * @version 2017-03-25
 */
@Service
@Transactional(readOnly = true)
public class SystemMessageService extends CrudService<SystemMessageDao, SystemMessage> {

	public SystemMessage get(String id) {
		return super.get(id);
	}
	
	public List<SystemMessage> findList(SystemMessage systemMessage) {
		return super.findList(systemMessage);
	}
	
	public Page<SystemMessage> findPage(Page<SystemMessage> page, SystemMessage systemMessage) {
		return super.findPage(page, systemMessage);
	}
	
	@Transactional(readOnly = false)
	public void save(SystemMessage systemMessage) {
		super.save(systemMessage);
	}
	
	@Transactional(readOnly = false)
	public void delete(SystemMessage systemMessage) {
		super.delete(systemMessage);
	}
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.msg.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.zkjd.ehua.system.msg.entity.SystemMessage;

/**
 * 平台消息DAO接口
 * @author yangsong
 * @version 2017-03-25
 */
@MyBatisDao
public interface SystemMessageDao extends CrudDao<SystemMessage> {
	
}
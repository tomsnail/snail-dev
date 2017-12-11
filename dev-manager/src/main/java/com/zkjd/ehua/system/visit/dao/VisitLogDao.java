/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.visit.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.zkjd.ehua.system.visit.entity.VisitLog;

/**
 * 访问日志DAO接口
 * @author yangsong
 * @version 2017-03-14
 */
@MyBatisDao
public interface VisitLogDao extends CrudDao<VisitLog> {
	
}
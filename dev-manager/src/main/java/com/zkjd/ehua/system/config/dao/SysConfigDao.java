/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.config.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.zkjd.ehua.system.config.entity.SysConfig;

/**
 * 系统配置DAO接口
 * @author yangsong
 * @version 2017-03-09
 */
@MyBatisDao
public interface SysConfigDao extends CrudDao<SysConfig> {
	
}
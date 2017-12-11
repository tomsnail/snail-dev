/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.conf.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.zkjd.ehua.system.conf.entity.VisitAddress;

/**
 * 访问地址DAO接口
 * @author yangsong
 * @version 2017-03-03
 */
@MyBatisDao
public interface VisitAddressDao extends CrudDao<VisitAddress> {
	
}
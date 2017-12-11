/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.dict.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.zkjd.ehua.system.dict.entity.DictAreaInfo;

/**
 * 地区DAO接口
 * @author yangsong
 * @version 2017-02-28
 */
@MyBatisDao
public interface DictAreaInfoDao extends TreeDao<DictAreaInfo> {
	
}
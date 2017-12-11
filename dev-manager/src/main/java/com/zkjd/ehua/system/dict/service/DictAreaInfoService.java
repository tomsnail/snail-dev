/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.dict.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.zkjd.ehua.system.dict.entity.DictAreaInfo;
import com.zkjd.ehua.system.dict.dao.DictAreaInfoDao;

/**
 * 地区Service
 * @author yangsong
 * @version 2017-02-28
 */
@Service
@Transactional(readOnly = true)
public class DictAreaInfoService extends TreeService<DictAreaInfoDao, DictAreaInfo> {

	public DictAreaInfo get(String id) {
		return super.get(id);
	}
	
	public List<DictAreaInfo> findList(DictAreaInfo dictAreaInfo) {
		if (StringUtils.isNotBlank(dictAreaInfo.getParentIds())){
			dictAreaInfo.setParentIds(","+dictAreaInfo.getParentIds()+",");
		}
		return super.findList(dictAreaInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(DictAreaInfo dictAreaInfo) {
		super.save(dictAreaInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(DictAreaInfo dictAreaInfo) {
		super.delete(dictAreaInfo);
	}
	
}
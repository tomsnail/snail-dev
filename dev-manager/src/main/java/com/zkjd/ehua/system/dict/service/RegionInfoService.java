/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.dict.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.zkjd.ehua.system.dict.entity.RegionInfo;
import com.zkjd.ehua.system.dict.dao.RegionInfoDao;

/**
 * 地区Service
 * @author yangsong
 * @version 2017-02-28
 */
@Service
@Transactional(readOnly = true)
public class RegionInfoService extends TreeService<RegionInfoDao, RegionInfo> {

	public RegionInfo get(String id) {
		return super.get(id);
	}
	
	public List<RegionInfo> findList(RegionInfo regionInfo) {
		if (StringUtils.isNotBlank(regionInfo.getParentIds())){
			regionInfo.setParentIds(","+regionInfo.getParentIds()+",");
		}
		return super.findList(regionInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(RegionInfo regionInfo) {
		if(!StringUtils.isBlank(regionInfo.getId())){
			CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.region_cache+regionInfo.getId());
		}
		super.save(regionInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(RegionInfo regionInfo) {
		super.delete(regionInfo);
	}
	
}
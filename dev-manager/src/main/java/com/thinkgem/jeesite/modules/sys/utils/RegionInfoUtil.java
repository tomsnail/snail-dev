package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.zkjd.ehua.system.dict.dao.RegionInfoDao;
import com.zkjd.ehua.system.dict.entity.RegionInfo;

public class RegionInfoUtil {

	private static final RegionInfoDao REGION_INFO_DAO = SpringContextHolder.getBean(RegionInfoDao.class);
	
	
	public static String getRegionName(String id){
		System.out.println(id);
		RegionInfo regionInfo = REGION_INFO_DAO.get(id);
		if(regionInfo!=null){
			return regionInfo.getRegionName();
		}
		return "未知";
	}
	
}

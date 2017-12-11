package com.zkjd.ehua.common.utils;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.zkjd.ehua.system.conf.dao.VisitAddressDao;
import com.zkjd.ehua.system.conf.entity.VisitAddress;
import com.zkjd.ehua.system.config.dao.SysConfigDao;
import com.zkjd.ehua.system.config.entity.SysConfig;
import com.zkjd.ehua.system.dict.dao.RegionInfoDao;
import com.zkjd.ehua.system.dict.entity.RegionInfo;

public class DataUtil {

	private static final RegionInfoDao REGION_INFO_DAO = SpringContextHolder.getBean(RegionInfoDao.class);
		

	private static final SysConfigDao SYS_CONFIG_DAO = SpringContextHolder.getBean(SysConfigDao.class);
	
	private static final VisitAddressDao VISIT_ADDRESS_DAO = SpringContextHolder.getBean(VisitAddressDao.class);
	
		
	
	
	
	public static String getRegionName(String id){
		RegionInfo regionInfo = (RegionInfo) CacheUtils.get(UserUtils.USER_CACHE, UserUtils.region_cache + id);
		if(regionInfo==null){
			regionInfo = REGION_INFO_DAO.get(id);
			CacheUtils.put(UserUtils.USER_CACHE, UserUtils.region_cache + id,regionInfo);
		}
		if(regionInfo!=null){
			return regionInfo.getRegionName();
		}
		return "";
	}

	public static String getSysConfig(String name,String type){
		SysConfig sysConfig = new SysConfig();
		sysConfig.setName(name);
		sysConfig.setType(type);
		List<SysConfig> sysConfigs = SYS_CONFIG_DAO.findList(sysConfig);
		if(sysConfigs!=null&&sysConfigs.size()==1){
			return sysConfigs.get(0).getValue();
		}
		return "";
	}
	
	public static String getNewDate(String pattern,Integer days){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE	, days);
		return DateFormatUtils.format(calendar.getTime(), pattern);
	}
	
	public static VisitAddress getVisitAddress(String name){
		VisitAddress entity = new VisitAddress();
		entity.setVisitAddr(name);
		List<VisitAddress> visitAddresses = VISIT_ADDRESS_DAO.findList(entity);
		if(visitAddresses!=null&&visitAddresses.size()==1){
			return visitAddresses.get(0);
		}
		return null;
	}
	

}

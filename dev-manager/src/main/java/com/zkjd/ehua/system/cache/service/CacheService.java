package com.zkjd.ehua.system.cache.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.sys.dao.DictDao;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.zkjd.ehua.common.utils.MQUtil;

@Service
@Transactional(readOnly = true)
public class CacheService {
	
	@Autowired
	private DictDao dictDao;

	public boolean clear(String value){
		try {
			String label =  DictUtils.getDictLabel(value, "redis_cache_type", "");
			JedisUtils.del(label);
			MQUtil.sendNodeMsg(value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}

package com.thinkgem.jeesite.modules.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.sys.dao.DictDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年3月3日 上午11:12:41
	* @see 
	*/     
@Service
public class DictRedisService extends BaseService{
	
	@Autowired
	private DictDao dictDao;

	public boolean updateCache(){
		
		Dict dict = new Dict();
		
		List<Dict> dicts = dictDao.findAllList(dict);
		
		try {
			if(dicts!=null&&dicts.size()>0){
				Map<String,List<Dict>> map = new HashMap<String,List<Dict>>();
				for(Dict t:dicts){
					if(t.getDelFlag().equals("0")){
						List<Dict> list = null;
						if(map.containsKey(t.getType())){
							list = map.get(t.getType());
						}else{
							list = new ArrayList<Dict>();
							map.put(t.getType(), list);
						}
						list.add(t);
					}
				}
				 Map<String, String> value = new HashMap<String, String>();
				Iterator<String> iterator = map.keySet().iterator();
				while(iterator.hasNext()){
					String key = iterator.next();
					value.put(key, JsonMapper.toJsonString(map.get(key)));
				}
				JedisUtils.setMap("dictmap", value, 0);
				return true;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return false;
	}
	
}

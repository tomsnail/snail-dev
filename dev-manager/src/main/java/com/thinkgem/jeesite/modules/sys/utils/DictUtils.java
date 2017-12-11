/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.dao.DictDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.zkjd.ehua.system.flow.dao.SysFlowDao;
import com.zkjd.ehua.system.flow.entity.SysFlow;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DictUtils {
	
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);
	
	private static SysFlowDao sysFlowDao = SpringContextHolder.getBean(SysFlowDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	
	public static String getDictLabel(String value){
		if (StringUtils.isNotBlank(value)){
			List<Dict> dicts = dictDao.findVlueList(value);
			if(dicts!=null&&dicts.size()>0){
				return dicts.get(0).getLabel();
			}
		}
		return "";
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<Dict> getDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	
	public static List<Dict> getFlowDictList(String startFlowId,String type){
		
		if(StringUtils.isBlank(startFlowId)){
			return getDictList(type);
		}
		
		List<Dict> dictList = null;
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		List<SysFlow> flows = sysFlowDao.getFlow(startFlowId);
		if(flows!=null&&flows.size()>0){
			SysFlow _sysFlow = flows.get(0);
			String idStr = _sysFlow.getNextFlowIds();
			String[] ids = idStr.split(",");
			for(String id:ids){
				List<Dict> dicts = dictDao.findVlueList(id);
				if(dicts!=null&&dicts.size()>0){
					dictList.add(dicts.get(0));
				}
				
			}
			
		}
		List<Dict> dicts = dictDao.findVlueList(startFlowId);
		if(dicts!=null&&dicts.size()>0){
			dictList.add(dicts.get(0));
		}
		
		
		return dictList;
	}
	
	
	public static List<Dict> getAllDictList(){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		
		List<Dict> dictList = new ArrayList<Dict>();
		
		if(dictMap!=null&&dictMap.size()>0){
			Iterator<String> keyIterator = dictMap.keySet().iterator();
			while(keyIterator.hasNext()){
				String key = keyIterator.next();
				dictList.addAll(dictMap.get(key));
			}
		}
		
		return dictList;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}
	
}

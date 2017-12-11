/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.dict.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.zkjd.ehua.system.dict.entity.RegionInfo;
import com.zkjd.ehua.system.dict.service.RegionInfoService;

/**
 * 地区Controller
 * @author yangsong
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/dict/regionInfo")
public class RegionInfoController extends BaseController {

	@Autowired
	private RegionInfoService regionInfoService;
	
	@ModelAttribute
	public RegionInfo get(@RequestParam(required=false) String id) {
		RegionInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = regionInfoService.get(id);
		}
		if (entity == null){
			entity = new RegionInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("dict:regionInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(RegionInfo regionInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<RegionInfo> list = regionInfoService.findList(regionInfo); 
		model.addAttribute("list", list);
		return "system/dict/regionInfoList";
	}

	@RequiresPermissions("dict:regionInfo:view")
	@RequestMapping(value = "form")
	public String form(RegionInfo regionInfo, Model model) {
		if (regionInfo.getParent()!=null && StringUtils.isNotBlank(regionInfo.getParent().getId())){
			regionInfo.setParent(regionInfoService.get(regionInfo.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(regionInfo.getId())){
				RegionInfo regionInfoChild = new RegionInfo();
				regionInfoChild.setParent(new RegionInfo(regionInfo.getParent().getId()));
				List<RegionInfo> list = regionInfoService.findList(regionInfo); 
				if (list.size() > 0){
					regionInfo.setSort(list.get(list.size()-1).getSort());
					if (regionInfo.getSort() != null){
						regionInfo.setSort(regionInfo.getSort() + 30);
					}
				}
			}
		}
		if (regionInfo.getSort() == null){
			regionInfo.setSort(30);
		}
		model.addAttribute("regionInfo", regionInfo);
		return "system/dict/regionInfoForm";
	}

	@RequiresPermissions("dict:regionInfo:edit")
	@RequestMapping(value = "save")
	public String save(RegionInfo regionInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, regionInfo)){
			return form(regionInfo, model);
		}
		regionInfoService.save(regionInfo);
		addMessage(redirectAttributes, "保存地区成功");
		return "redirect:"+Global.getAdminPath()+"/dict/regionInfo/?repage";
	}
	
	@RequiresPermissions("dict:regionInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(RegionInfo regionInfo, RedirectAttributes redirectAttributes) {
		regionInfoService.delete(regionInfo);
		addMessage(redirectAttributes, "删除地区成功");
		return "redirect:"+Global.getAdminPath()+"/dict/regionInfo/?repage";
	}
	
	@RequiresPermissions("dict:regionInfo:edit")
	@RequestMapping(value = "updateCache")
	public String updateCache(RegionInfo regionInfo, RedirectAttributes redirectAttributes) {
		//regionInfoService.delete(regionInfo);
		CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.region_tree_cache);
		cachetree(null);
		addMessage(redirectAttributes, "更新缓存成功");
		return "redirect:"+Global.getAdminPath()+"/dict/regionInfo/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		return cachetree(extId);
	}

	private List<Map<String, Object>> cachetree(String extId) {
		
		if(StringUtils.isBlank(extId)){
			List<Map<String, Object>> mapList  = (List<Map<String, Object>>) CacheUtils.get(UserUtils.USER_CACHE, UserUtils.region_tree_cache);
			if(mapList==null){
				mapList = Lists.newArrayList();
				List<RegionInfo> list = regionInfoService.findList(new RegionInfo());
				for (int i=0; i<list.size(); i++){
					RegionInfo e = list.get(i);
					Map<String, Object> map = Maps.newHashMap();
					map.put("id", e.getId());
					map.put("pId", e.getParentId());
					map.put("name", e.getRegionName());
					mapList.add(map);
				}
				CacheUtils.put(UserUtils.USER_CACHE, UserUtils.region_tree_cache,mapList);
			}
			return mapList;
		}else{
			List<Map<String, Object>> mapList  = (List<Map<String, Object>>) CacheUtils.get(UserUtils.USER_CACHE, UserUtils.region_tree_cache+extId);
			if(mapList==null){
				mapList = Lists.newArrayList();
				List<RegionInfo> list = regionInfoService.findList(new RegionInfo());
				for (int i=0; i<list.size(); i++){
					RegionInfo e = list.get(i);
					if(!StringUtils.isBlank(extId)){
						if(extId.equals(e.getParent().getId())){
							Map<String, Object> map = Maps.newHashMap();
							map.put("id", e.getId());
							map.put("pId", e.getParentId());
							map.put("name", e.getRegionName());
							mapList.add(map);
						}
					}else{
						Map<String, Object> map = Maps.newHashMap();
						map.put("id", e.getId());
						map.put("pId", e.getParentId());
						map.put("name", e.getRegionName());
						mapList.add(map);
					}
					
				}
				CacheUtils.put(UserUtils.USER_CACHE, UserUtils.region_tree_cache+extId,mapList);
			}
			return mapList;
		}
		
		
	}
	
}
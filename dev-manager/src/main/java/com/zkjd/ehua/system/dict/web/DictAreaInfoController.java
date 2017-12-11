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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.zkjd.ehua.system.dict.entity.DictAreaInfo;
import com.zkjd.ehua.system.dict.service.DictAreaInfoService;

/**
 * 地区Controller
 * @author yangsong
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/dict/dictAreaInfo")
public class DictAreaInfoController extends BaseController {

	@Autowired
	private DictAreaInfoService dictAreaInfoService;
	
	@ModelAttribute
	public DictAreaInfo get(@RequestParam(required=false) String id) {
		DictAreaInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dictAreaInfoService.get(id);
		}
		if (entity == null){
			entity = new DictAreaInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("dict:dictAreaInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(DictAreaInfo dictAreaInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<DictAreaInfo> list = dictAreaInfoService.findList(dictAreaInfo); 
		model.addAttribute("list", list);
		return "system/dict/dictAreaInfoList";
	}

	@RequiresPermissions("dict:dictAreaInfo:view")
	@RequestMapping(value = "form")
	public String form(DictAreaInfo dictAreaInfo, Model model) {
		if (dictAreaInfo.getParent()!=null && StringUtils.isNotBlank(dictAreaInfo.getParent().getId())){
			dictAreaInfo.setParent(dictAreaInfoService.get(dictAreaInfo.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(dictAreaInfo.getId())){
				DictAreaInfo dictAreaInfoChild = new DictAreaInfo();
				dictAreaInfoChild.setParent(new DictAreaInfo(dictAreaInfo.getParent().getId()));
				List<DictAreaInfo> list = dictAreaInfoService.findList(dictAreaInfo); 
				if (list.size() > 0){
					dictAreaInfo.setSort(list.get(list.size()-1).getSort());
					if (dictAreaInfo.getSort() != null){
						dictAreaInfo.setSort(dictAreaInfo.getSort() + 30);
					}
				}
			}
		}
		if (dictAreaInfo.getSort() == null){
			dictAreaInfo.setSort(30);
		}
		model.addAttribute("dictAreaInfo", dictAreaInfo);
		return "system/dict/dictAreaInfoForm";
	}

	@RequiresPermissions("dict:dictAreaInfo:edit")
	@RequestMapping(value = "save")
	public String save(DictAreaInfo dictAreaInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dictAreaInfo)){
			return form(dictAreaInfo, model);
		}
		dictAreaInfoService.save(dictAreaInfo);
		addMessage(redirectAttributes, "保存地区成功");
		return "redirect:"+Global.getAdminPath()+"/dict/dictAreaInfo/?repage";
	}
	
	@RequiresPermissions("dict:dictAreaInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(DictAreaInfo dictAreaInfo, RedirectAttributes redirectAttributes) {
		dictAreaInfoService.delete(dictAreaInfo);
		addMessage(redirectAttributes, "删除地区成功");
		return "redirect:"+Global.getAdminPath()+"/dict/dictAreaInfo/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<DictAreaInfo> list = dictAreaInfoService.findList(new DictAreaInfo());
		for (int i=0; i<list.size(); i++){
			DictAreaInfo e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}
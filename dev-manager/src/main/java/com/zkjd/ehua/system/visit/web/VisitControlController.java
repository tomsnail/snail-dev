/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.visit.web;


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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.zkjd.ehua.system.visit.entity.VisitControl;
import com.zkjd.ehua.system.visit.service.VisitControlRedisService;
import com.zkjd.ehua.system.visit.service.VisitControlService;

/**
 * 访问控制Controller
 * @author yangsong
 * @version 2017-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/visit/visitControl")
public class VisitControlController extends BaseController {

	@Autowired
	private VisitControlService visitControlService;
	
	@Autowired
	private VisitControlRedisService visitControlRedisService;
	
	@ModelAttribute
	public VisitControl get(@RequestParam(required=false) String id) {
		VisitControl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = visitControlService.get(id);
		}
		if (entity == null){
			entity = new VisitControl();
		}
		return entity;
	}
	
	@RequiresPermissions("visit:visitControl:view")
	@RequestMapping(value = {"list", ""})
	public String list(VisitControl visitControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VisitControl> page = visitControlService.findPage(new Page<VisitControl>(request, response), visitControl); 
		model.addAttribute("page", page);
		return "system/visit/visitControlList";
	}

	@RequiresPermissions("visit:visitControl:view")
	@RequestMapping(value = "form")
	public String form(VisitControl visitControl, Model model) {
		model.addAttribute("visitControl", visitControl);
		return "system/visit/visitControlForm";
	}

	@RequiresPermissions("visit:visitControl:edit")
	@RequestMapping(value = "save")
	public String save(VisitControl visitControl, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, visitControl)){
			return form(visitControl, model);
		}
		visitControlService.save(visitControl);
		addMessage(redirectAttributes, "保存访问控制成功");
		return "redirect:"+Global.getAdminPath()+"/visit/visitControl/?repage";
	}
	
	@RequiresPermissions("visit:visitControl:edit")
	@RequestMapping(value = "delete")
	public String delete(VisitControl visitControl, RedirectAttributes redirectAttributes) {
		visitControlService.delete(visitControl);
		addMessage(redirectAttributes, "删除访问控制成功");
		return "redirect:"+Global.getAdminPath()+"/visit/visitControl/?repage";
	}
	
	
	@RequestMapping(value = "refreshCache")
	@ResponseBody
	public String refreshCache() {
		return visitControlRedisService.refresh();
	}
	
	@RequestMapping(value = "clearCache")
	@ResponseBody
	public String clearCache() {
		return visitControlRedisService.clear();
	}

}
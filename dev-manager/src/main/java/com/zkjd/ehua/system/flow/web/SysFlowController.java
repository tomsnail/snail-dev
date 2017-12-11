/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.flow.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.zkjd.ehua.system.flow.entity.SysFlow;
import com.zkjd.ehua.system.flow.service.SysFlowService;

/**
 * 系统流程Controller
 * @author yangsong
 * @version 2017-03-01
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/sysFlow")
public class SysFlowController extends BaseController {

	@Autowired
	private SysFlowService sysFlowService;
	
	@ModelAttribute
	public SysFlow get(@RequestParam(required=false) String id) {
		SysFlow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysFlowService.get(id);
		}
		if (entity == null){
			entity = new SysFlow();
		}
		return entity;
	}
	
	@RequiresPermissions("flow:sysFlow:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysFlow sysFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysFlow> page = sysFlowService.findPage(new Page<SysFlow>(request, response), sysFlow); 
		model.addAttribute("page", page);
		return "system/flow/sysFlowList";
	}

	@RequiresPermissions("flow:sysFlow:view")
	@RequestMapping(value = "form")
	public String form(SysFlow sysFlow, Model model) {
		model.addAttribute("sysFlow", sysFlow);
		return "system/flow/sysFlowForm";
	}

	@RequiresPermissions("flow:sysFlow:edit")
	@RequestMapping(value = "save")
	public String save(SysFlow sysFlow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysFlow)){
			return form(sysFlow, model);
		}
		sysFlowService.save(sysFlow);
		addMessage(redirectAttributes, "保存系统流程成功");
		return "redirect:"+Global.getAdminPath()+"/flow/sysFlow/?repage";
	}
	
	@RequiresPermissions("flow:sysFlow:edit")
	@RequestMapping(value = "delete")
	public String delete(SysFlow sysFlow, RedirectAttributes redirectAttributes) {
		sysFlowService.delete(sysFlow);
		addMessage(redirectAttributes, "删除系统流程成功");
		return "redirect:"+Global.getAdminPath()+"/flow/sysFlow/?repage";
	}

}
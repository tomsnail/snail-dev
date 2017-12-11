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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.zkjd.ehua.system.visit.entity.VisitLog;
import com.zkjd.ehua.system.visit.service.VisitLogService;

/**
 * 访问日志Controller
 * @author yangsong
 * @version 2017-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/visit/visitLog")
public class VisitLogController extends BaseController {

	@Autowired
	private VisitLogService visitLogService;
	
	@ModelAttribute
	public VisitLog get(@RequestParam(required=false) String id) {
		VisitLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = visitLogService.get(id);
		}
		if (entity == null){
			entity = new VisitLog();
		}
		return entity;
	}
	
	@RequiresPermissions("visit:visitLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(VisitLog visitLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VisitLog> page = visitLogService.findPage(new Page<VisitLog>(request, response), visitLog); 
		model.addAttribute("page", page);
		return "system/visit/visitLogList";
	}

	@RequiresPermissions("visit:visitLog:view")
	@RequestMapping(value = "form")
	public String form(VisitLog visitLog, Model model) {
		model.addAttribute("visitLog", visitLog);
		return "system/visit/visitLogForm";
	}

	@RequiresPermissions("visit:visitLog:edit")
	@RequestMapping(value = "save")
	public String save(VisitLog visitLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, visitLog)){
			return form(visitLog, model);
		}
		visitLogService.save(visitLog);
		addMessage(redirectAttributes, "保存访问日志成功");
		return "redirect:"+Global.getAdminPath()+"/visit/visitLog/?repage";
	}
	
	@RequiresPermissions("visit:visitLog:edit")
	@RequestMapping(value = "delete")
	public String delete(VisitLog visitLog, RedirectAttributes redirectAttributes) {
		visitLogService.delete(visitLog);
		addMessage(redirectAttributes, "删除访问日志成功");
		return "redirect:"+Global.getAdminPath()+"/visit/visitLog/?repage";
	}

}
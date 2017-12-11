/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.dict.web;

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
import com.zkjd.ehua.system.dict.entity.ExpressCompany;
import com.zkjd.ehua.system.dict.service.ExpressCompanyService;

/**
 * 快递公司Controller
 * @author yangsong
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/dict/expressCompany")
public class ExpressCompanyController extends BaseController {

	@Autowired
	private ExpressCompanyService expressCompanyService;
	
	@ModelAttribute
	public ExpressCompany get(@RequestParam(required=false) String id) {
		ExpressCompany entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = expressCompanyService.get(id);
		}
		if (entity == null){
			entity = new ExpressCompany();
		}
		return entity;
	}
	
	@RequiresPermissions("dict:expressCompany:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExpressCompany expressCompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ExpressCompany> page = expressCompanyService.findPage(new Page<ExpressCompany>(request, response), expressCompany); 
		model.addAttribute("page", page);
		return "system/dict/expressCompanyList";
	}

	@RequiresPermissions("dict:expressCompany:view")
	@RequestMapping(value = "form")
	public String form(ExpressCompany expressCompany, Model model) {
		model.addAttribute("expressCompany", expressCompany);
		return "system/dict/expressCompanyForm";
	}

	@RequiresPermissions("dict:expressCompany:edit")
	@RequestMapping(value = "save")
	public String save(ExpressCompany expressCompany, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, expressCompany)){
			return form(expressCompany, model);
		}
		expressCompanyService.save(expressCompany);
		addMessage(redirectAttributes, "保存快递公司成功");
		return "redirect:"+Global.getAdminPath()+"/dict/expressCompany/?repage";
	}
	
	@RequiresPermissions("dict:expressCompany:edit")
	@RequestMapping(value = "delete")
	public String delete(ExpressCompany expressCompany, RedirectAttributes redirectAttributes) {
		expressCompanyService.delete(expressCompany);
		addMessage(redirectAttributes, "删除快递公司成功");
		return "redirect:"+Global.getAdminPath()+"/dict/expressCompany/?repage";
	}

}
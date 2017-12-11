/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.msgtemp.web;

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
import com.zkjd.ehua.system.msgtemp.entity.MsgTemplate;
import com.zkjd.ehua.system.msgtemp.service.MsgTemplateService;

/**
 * 消息模板Controller
 * @author yangsong
 * @version 2017-03-25
 */
@Controller
@RequestMapping(value = "${adminPath}/msgtemp/msgTemplate")
public class MsgTemplateController extends BaseController {

	@Autowired
	private MsgTemplateService msgTemplateService;
	
	@ModelAttribute
	public MsgTemplate get(@RequestParam(required=false) String id) {
		MsgTemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = msgTemplateService.get(id);
		}
		if (entity == null){
			entity = new MsgTemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("msgtemp:msgTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(MsgTemplate msgTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MsgTemplate> page = msgTemplateService.findPage(new Page<MsgTemplate>(request, response), msgTemplate); 
		model.addAttribute("page", page);
		return "system/msgtemp/msgTemplateList";
	}

	@RequiresPermissions("msgtemp:msgTemplate:view")
	@RequestMapping(value = "form")
	public String form(MsgTemplate msgTemplate, Model model) {
		model.addAttribute("msgTemplate", msgTemplate);
		return "system/msgtemp/msgTemplateForm";
	}

	@RequiresPermissions("msgtemp:msgTemplate:edit")
	@RequestMapping(value = "save")
	public String save(MsgTemplate msgTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, msgTemplate)){
			return form(msgTemplate, model);
		}
		msgTemplateService.save(msgTemplate);
		addMessage(redirectAttributes, "保存消息模板成功");
		return "redirect:"+Global.getAdminPath()+"/msgtemp/msgTemplate/?repage";
	}
	
	@RequiresPermissions("msgtemp:msgTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(MsgTemplate msgTemplate, RedirectAttributes redirectAttributes) {
		msgTemplateService.delete(msgTemplate);
		addMessage(redirectAttributes, "删除消息模板成功");
		return "redirect:"+Global.getAdminPath()+"/msgtemp/msgTemplate/?repage";
	}

}
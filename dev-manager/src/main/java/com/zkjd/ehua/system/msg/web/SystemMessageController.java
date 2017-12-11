/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.msg.web;

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
import com.zkjd.ehua.system.msg.entity.SystemMessage;
import com.zkjd.ehua.system.msg.service.SystemMessageService;

/**
 * 平台消息Controller
 * @author yangsong
 * @version 2017-03-25
 */
@Controller
@RequestMapping(value = "${adminPath}/msg/systemMessage")
public class SystemMessageController extends BaseController {

	@Autowired
	private SystemMessageService systemMessageService;
	
	@ModelAttribute
	public SystemMessage get(@RequestParam(required=false) String id) {
		SystemMessage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = systemMessageService.get(id);
		}
		if (entity == null){
			entity = new SystemMessage();
		}
		return entity;
	}
	
	@RequiresPermissions("msg:systemMessage:view")
	@RequestMapping(value = {"list", ""})
	public String list(SystemMessage systemMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SystemMessage> page = systemMessageService.findPage(new Page<SystemMessage>(request, response), systemMessage); 
		model.addAttribute("page", page);
		return "system/msg/systemMessageList";
	}

	@RequiresPermissions("msg:systemMessage:view")
	@RequestMapping(value = "form")
	public String form(SystemMessage systemMessage, Model model) {
		model.addAttribute("systemMessage", systemMessage);
		return "system/msg/systemMessageForm";
	}

	@RequiresPermissions("msg:systemMessage:edit")
	@RequestMapping(value = "save")
	public String save(SystemMessage systemMessage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, systemMessage)){
			return form(systemMessage, model);
		}
		systemMessageService.save(systemMessage);
		addMessage(redirectAttributes, "保存平台消息成功");
		return "redirect:"+Global.getAdminPath()+"/msg/systemMessage/?repage";
	}
	
	@RequiresPermissions("msg:systemMessage:edit")
	@RequestMapping(value = "delete")
	public String delete(SystemMessage systemMessage, RedirectAttributes redirectAttributes) {
		systemMessageService.delete(systemMessage);
		addMessage(redirectAttributes, "删除平台消息成功");
		return "redirect:"+Global.getAdminPath()+"/msg/systemMessage/?repage";
	}

}
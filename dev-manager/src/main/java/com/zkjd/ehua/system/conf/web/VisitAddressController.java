/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.conf.web;

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
import com.zkjd.ehua.system.conf.entity.VisitAddress;
import com.zkjd.ehua.system.conf.service.VisitAddressRedisService;
import com.zkjd.ehua.system.conf.service.VisitAddressService;

/**
 * 访问地址Controller
 * @author yangsong
 * @version 2017-03-03
 */
@Controller
@RequestMapping(value = "${adminPath}/conf/visitAddress")
public class VisitAddressController extends BaseController {

	@Autowired
	private VisitAddressService visitAddressService;
	
	@Autowired
	private VisitAddressRedisService addressRedisService;
	
	@ModelAttribute
	public VisitAddress get(@RequestParam(required=false) String id) {
		VisitAddress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = visitAddressService.get(id);
		}
		if (entity == null){
			entity = new VisitAddress();
		}
		return entity;
	}
	
	@RequiresPermissions("conf:visitAddress:view")
	@RequestMapping(value = {"list", ""})
	public String list(VisitAddress visitAddress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VisitAddress> page = visitAddressService.findPage(new Page<VisitAddress>(request, response), visitAddress); 
		model.addAttribute("page", page);
		return "system/conf/visitAddressList";
	}

	@RequiresPermissions("conf:visitAddress:view")
	@RequestMapping(value = "form")
	public String form(VisitAddress visitAddress, Model model) {
		model.addAttribute("visitAddress", visitAddress);
		return "system/conf/visitAddressForm";
	}

	@RequiresPermissions("conf:visitAddress:edit")
	@RequestMapping(value = "save")
	public String save(VisitAddress visitAddress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, visitAddress)){
			return form(visitAddress, model);
		}
		visitAddressService.save(visitAddress);
		addMessage(redirectAttributes, "保存访问地址成功");
		return "redirect:"+Global.getAdminPath()+"/conf/visitAddress/?repage";
	}
	
	@RequiresPermissions("conf:visitAddress:edit")
	@RequestMapping(value = "delete")
	public String delete(VisitAddress visitAddress, RedirectAttributes redirectAttributes) {
		visitAddressService.delete(visitAddress);
		addMessage(redirectAttributes, "删除访问地址成功");
		return "redirect:"+Global.getAdminPath()+"/conf/visitAddress/?repage";
	}
	
	@RequiresPermissions("conf:visitAddress:edit")
	@RequestMapping(value = "useOper")
	public String useOper(@RequestParam(required=false) String id,@RequestParam(required=false) String isRelease,RedirectAttributes redirectAttributes) {
		VisitAddress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = visitAddressService.get(id);
		}
		if(entity==null){
			addMessage(redirectAttributes, "数据错误");
		}else{
			entity.setIsRelease(isRelease);
			visitAddressService.save(entity);
			
			if(addressRedisService.userOper(entity)){
				addMessage(redirectAttributes, "更新成功");
			}else{
				addMessage(redirectAttributes, "更新失败");
			}
		}
		return "redirect:"+Global.getAdminPath()+"/conf/visitAddress/?repage";
	}
	
	@RequiresPermissions("conf:visitAddress:edit")
	@RequestMapping(value = "updateCache")
	public String updateCache(RedirectAttributes redirectAttributes) {
		if(addressRedisService.refresh()){
			addMessage(redirectAttributes, "更新成功");
		}else{
			addMessage(redirectAttributes, "更新失败");
		}
		return "redirect:"+Global.getAdminPath()+"/conf/visitAddress/?repage";
	}

}
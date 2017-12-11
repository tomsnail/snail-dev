/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.cache.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.zkjd.ehua.system.cache.service.CacheService;



/**
 * 缓存Controller
 * @author yangsong
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/cache/cache")
public class CacheController extends BaseController {
	
	@Autowired
	private CacheService cacheService;

	
	@RequestMapping(value = "clear")
	@ResponseBody
	public String clear(@RequestParam(required=true) String value){
		if(!cacheService.clear(value)){
			return "操作失败";
		}
		return "操作成功";
	}
	

}
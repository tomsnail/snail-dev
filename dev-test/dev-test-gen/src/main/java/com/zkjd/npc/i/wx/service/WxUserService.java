/**
 * Copyright &copy; 2012-2016 
 */
package com.zkjd.npc.i.wx.service;

import java.util.List;

import cn.tomsnail.framwork.core.page.Page;

import com.zkjd.npc.i.wx.model.WxUserMo;


/**
 * Service接口
 * @author Wangpeng
 * @version 2018-11-15
 */
public interface WxUserService  {
	
	public List<WxUserMo> findList(WxUserMo wxUser);
		
	public WxUserMo get(String id);
	
	public int findPageCount(WxUserMo wxUser);
	
	public List<WxUserMo> findPage(WxUserMo wxUser,Page page);
	
	public boolean save(WxUserMo wxUser);
	
	public boolean saveBatch(List<WxUserMo> wxUsers);
	
	public boolean delete(String id);
	
	public boolean deleteBatch(List<String> ids);
	
	public boolean update(WxUserMo wxUser);
	
	public boolean updateBatch(List<WxUserMo> wxUsers);
	
}
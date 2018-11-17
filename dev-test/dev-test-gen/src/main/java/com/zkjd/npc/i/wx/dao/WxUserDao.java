/**
 * Copyright &copy; 2012-2016 
 */
package com.zkjd.npc.i.wx.dao;

import java.util.List;

import com.zkjd.npc.i.wx.model.WxUserDto;

import cn.tomsnail.dao.plugins.pagination.PageDao;

/**
 * DAO接口
 * @author Wangpeng
 * @version 2018-11-15
 */
public interface WxUserDao extends PageDao<WxUserDto> {
	
	public List<WxUserDto> findList(WxUserDto wxUserDto);
	
	public List<WxUserDto> findAllList();
	
	public WxUserDto get(String id);
	
	public int findPageCount(WxUserDto wxUserDto);
	
	public int insert(WxUserDto wxUserDto);
	
	public int delete(String id);
	
	public int update(WxUserDto wxUserDto);
	
}
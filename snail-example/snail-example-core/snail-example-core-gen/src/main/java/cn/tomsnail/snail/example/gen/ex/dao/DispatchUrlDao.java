/**
 * Copyright &copy; 2012-2016 
 */
package cn.tomsnail.snail.example.gen.ex.dao;

import java.util.List;

import cn.tomsnail.snail.example.gen.ex.model.DispatchUrlDto;

import cn.tomsnail.snail.core.ds.dao.plugins.pagination.PageDao;

/**
 * DispatchUrlDAO接口
 * @author Yangsong
 * @version 0.0.1-SNAPSHOT
 */
public interface DispatchUrlDao extends PageDao<DispatchUrlDto> {
	
	public List<DispatchUrlDto> findList(DispatchUrlDto dispatchUrlDto);
	
	public List<DispatchUrlDto> findAllList();
	
	public DispatchUrlDto get(String id);
	
	public int findPageCount(DispatchUrlDto dispatchUrlDto);
	
	public int insert(DispatchUrlDto dispatchUrlDto);
	
	public int delete(String id);
	
	public int update(DispatchUrlDto dispatchUrlDto);
	
}
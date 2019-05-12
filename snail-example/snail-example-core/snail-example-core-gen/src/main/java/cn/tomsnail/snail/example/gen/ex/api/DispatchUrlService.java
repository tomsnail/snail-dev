/**
 * Copyright &copy; 2012-2016 
 */
package cn.tomsnail.snail.example.gen.ex.api;

import java.util.List;

import cn.tomsnail.snail.core.page.Page;


/**
 * DispatchUrlService接口
 * @author Yangsong
 * @version 0.0.1-SNAPSHOT
 */
public interface DispatchUrlService  {
	
	public List<DispatchUrlMo> findList(DispatchUrlMo dispatchUrl);
		
	public DispatchUrlMo get(String id);
	
	public int findPageCount(DispatchUrlMo dispatchUrl);
	
	public List<DispatchUrlMo> findPage(DispatchUrlMo dispatchUrl, Page page);
	
	public boolean save(DispatchUrlMo dispatchUrl);
	
	public boolean saveBatch(List<DispatchUrlMo> dispatchUrls);
	
	public boolean delete(String id);
	
	public boolean deleteBatch(List<String> ids);
	
	public boolean update(DispatchUrlMo dispatchUrl);
	
	public boolean updateBatch(List<DispatchUrlMo> dispatchUrls);
	
}
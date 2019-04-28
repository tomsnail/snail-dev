/**
 * Copyright &copy; 2012-2016 
 */
package cn.tomsnail.snail.example.core.framework.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tomsnail.snai.core.ds.dao.plugins.pagination.PageDao;
import cn.tomsnail.snail.example.core.framework.model.DispatchStrategyDto;

/**
 * DAO接口
 * @author Yangsong
 * @version 2018-09-06
 */
@Repository
public interface DispatchStrategyDao extends PageDao<DispatchStrategyDto> {
	
	public List<DispatchStrategyDto> findList(DispatchStrategyDto dispatchStrategy);
	
	public List<DispatchStrategyDto> findAllList();
	
	public DispatchStrategyDto get(String id);
	
	public int findPageCount(DispatchStrategyDto dispatchStrategy);
	
	public int insert(DispatchStrategyDto dispatchStrategy);
	
	public int delete(String id);
	
	public int update(DispatchStrategyDto dispatchStrategy);
	
}
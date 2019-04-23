package cn.tomsnail.snai.ext.ds.dao.plugins.pagination;

import java.util.List;

import cn.tomsnail.snail.core.obj.base.BaseDao;


public interface PageDao<T> extends BaseDao{

	public List<T> findList(T entity);
	
}

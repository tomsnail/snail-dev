package cn.tomsnail.dao.plugins.pagination;

import java.util.List;

import cn.tomsnail.obj.base.BaseDao;

public interface PageDao<T> extends BaseDao{

	public List<T> findList(T entity);
	
}

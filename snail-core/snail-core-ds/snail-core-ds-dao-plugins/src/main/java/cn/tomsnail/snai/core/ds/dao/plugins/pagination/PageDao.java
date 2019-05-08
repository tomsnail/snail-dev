package cn.tomsnail.snai.core.ds.dao.plugins.pagination;

import java.util.List;


public interface PageDao<T> {

	public List<T> findList(T entity);
	
}

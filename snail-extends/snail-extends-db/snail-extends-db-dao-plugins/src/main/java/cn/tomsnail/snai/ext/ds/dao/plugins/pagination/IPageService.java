package cn.tomsnail.snai.ext.ds.dao.plugins.pagination;

public interface IPageService<T> {
	
	
	public Page<T> page(T e,int start,int limit);
	
	
	public Page<T> page(T e);

}

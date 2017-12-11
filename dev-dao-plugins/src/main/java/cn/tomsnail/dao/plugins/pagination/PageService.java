package cn.tomsnail.dao.plugins.pagination;




import org.springframework.beans.factory.annotation.Autowired;

import cn.tomsnail.obj.base.BaseService;


/**
 * Page Service
 */
public  class PageService<D extends PageDao<T>,T extends PageModel<T>> extends BaseService implements IPageService<T>{
	
	@Autowired
	protected D dao;
	
	@Override
	public Page<T> page(T e,int start,int limit){
		Page<T> page = new Page<T>(start, limit);
		e.setPage(page);
		page.setList(dao.findList(e));
		return page;
	}
	
	@Override
	public Page<T> page(T e){
		return page(e,0,10);
	}
	

}

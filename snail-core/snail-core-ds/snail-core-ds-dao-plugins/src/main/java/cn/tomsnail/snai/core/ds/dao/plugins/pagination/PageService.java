package cn.tomsnail.snai.core.ds.dao.plugins.pagination;




import cn.tomsnail.snail.core.obj.base.BaseTransactionComponent;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Page Service
 */
public  class PageService<D extends PageDao<T>,T extends PageModel<T>> extends BaseTransactionComponent implements IPageService<T>{
	
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

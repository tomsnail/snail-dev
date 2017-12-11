package cn.tomsnail.dao.plugins.pagination;

import cn.tomsnail.dao.plugins.pagination.Page;
import cn.tomsnail.obj.base.BaseModel;

public class PageModel<T> extends BaseModel{
	
	
	protected Page<T> page;

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

}

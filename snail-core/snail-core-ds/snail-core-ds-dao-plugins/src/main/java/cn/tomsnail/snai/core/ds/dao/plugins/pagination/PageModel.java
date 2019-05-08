package cn.tomsnail.snai.core.ds.dao.plugins.pagination;

import cn.tomsnail.snail.core.obj.base.BaseModel;

public class PageModel<T> extends BaseModel {
	
	
	protected Page<T> page;

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

}

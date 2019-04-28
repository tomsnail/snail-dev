package cn.tomsnail.snai.core.ds.dao.plugins.pagination.demo;

import cn.tomsnail.snai.core.ds.dao.plugins.pagination.PageModel;

public class DemoModel extends PageModel<DemoModel>{
	
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}

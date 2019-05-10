package cn.tomsnail.snail.core.gen.template;

import java.util.ArrayList;
import java.util.List;

public class GenClass {
	
	public String className;
	
	private List<GenMethod> columnList = new ArrayList<GenMethod>();

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<GenMethod> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<GenMethod> columnList) {
		this.columnList = columnList;
	}


}

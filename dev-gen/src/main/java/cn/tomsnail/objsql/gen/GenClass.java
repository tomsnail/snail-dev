package cn.tomsnail.objsql.gen;

import java.util.ArrayList;
import java.util.List;

public class GenClass {
	
	public String className;
	
	private List<GenMethod> columnList = new ArrayList<GenMethod>();
	
	private List<String> importList = new ArrayList<String>();

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

	public List<String> getImportList() {
		return importList;
	}

	public void setImportList(List<String> importList) {
		this.importList = importList;
	}

}

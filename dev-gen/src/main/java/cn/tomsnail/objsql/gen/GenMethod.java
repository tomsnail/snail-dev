package cn.tomsnail.objsql.gen;

import java.util.List;

public class GenMethod {

	private String name;
	
	private String uname;
	
	private int argLength = 0;
	
	private  String genParamters;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArgLength() {
		return argLength;
	}

	public void setArgLength(int argLength) {
		this.argLength = argLength;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getGenParamters() {
		return genParamters;
	}

	public void setGenParamters(String genParamters) {
		this.genParamters = genParamters;
	}

	
	
}

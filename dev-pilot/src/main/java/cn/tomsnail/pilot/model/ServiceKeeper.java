package cn.tomsnail.pilot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ServiceKeeper implements Serializable{

	private String name;
	
	private String limitNumber;
	
	private String currentCount = "0";
	
	private List<ServiceProcess> nodes = new ArrayList<ServiceProcess>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(String limitNumber) {
		this.limitNumber = limitNumber;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}

	public List<ServiceProcess> getNodes() {
		return nodes;
	}

	public void setNodes(List<ServiceProcess> nodes) {
		this.nodes = nodes;
	}
}

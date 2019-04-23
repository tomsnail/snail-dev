package cn.tomsnail.snail.ext.pilot.core.model;

import java.util.List;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月6日 下午4:35:34
 * @see 
 */
public class ServiceProcessModel {

	private String node;
	
	private List<ServiceProcess> serviceProcesses;

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<ServiceProcess> getServiceProcesses() {
		return serviceProcesses;
	}

	public void setServiceProcesses(List<ServiceProcess> serviceProcesses) {
		this.serviceProcesses = serviceProcesses;
	}
	
}

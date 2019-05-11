package cn.tomsnail.snail.ext.pilot.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月6日 下午2:07:31
 * @see 
 */
@SuppressWarnings("serial")
public class SmartProxyInfo extends ProxyInfo implements Serializable{

	private List<ServiceProcess> configList = new ArrayList<ServiceProcess>();

	public List<ServiceProcess> getConfigList() {
		return configList;
	}

	public void setConfigList(List<ServiceProcess> configList) {
		this.configList = configList;
	}
	
	
	
	
}

package cn.tomsnail.pilot.model;

import java.io.Serializable;
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
public class ProxyInfo implements Serializable{

	private ServiceProcess selfInfo;
	
	private List<ServiceProcess>  commandInfo;

	public ServiceProcess getSelfInfo() {
		return selfInfo;
	}

	public void setSelfInfo(ServiceProcess selfInfo) {
		this.selfInfo = selfInfo;
	}

	public List<ServiceProcess> getCommandInfo() {
		return commandInfo;
	}

	public void setCommandInfo(List<ServiceProcess> commandInfo) {
		this.commandInfo = commandInfo;
	}
}

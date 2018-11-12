package cn.tomsnail.test.dubbo.rest.mo;

import java.io.Serializable;

public class RequestData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2384694398645794354L;

	private String version;
	
	private String command;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	

}

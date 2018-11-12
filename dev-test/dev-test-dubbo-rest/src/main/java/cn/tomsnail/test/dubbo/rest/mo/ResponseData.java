package cn.tomsnail.test.dubbo.rest.mo;

import java.io.Serializable;

public class ResponseData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8602477830491218102L;

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

	public ResponseData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseData(String command) {
		super();
		this.command = command;
	}
	
	

}

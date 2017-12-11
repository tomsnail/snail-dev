package cn.tomsnail.pilot.model;

import java.io.Serializable;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月5日 下午2:48:38
 * @see 
 */
@SuppressWarnings("serial")
public class ServiceProcess implements Serializable,Cloneable{

	private String name;
	
	private String alias;
	
	private String ip;
	
	private String node;
	
	private String port;
	
	private String rootDir;
	
	private String startFile;
	
	private String stopFile;
	
	private String logFile;
	
	private String status = ServiceStatus.UNKNOW;
	
	private String command;
	
	private String processNumber;
	
	private String remark;
	
	private String reportContent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	public String getStartFile() {
		return startFile;
	}

	public void setStartFile(String startFile) {
		this.startFile = startFile;
	}

	public String getStopFile() {
		return stopFile;
	}

	public void setStopFile(String stopFile) {
		this.stopFile = stopFile;
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getProcessNumber() {
		return processNumber;
	}

	public void setProcessNumber(String processNumber) {
		this.processNumber = processNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}

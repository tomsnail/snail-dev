package cn.tomsnail.config.server;

import java.io.Serializable;

/**
 *        配置模型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月4日 下午1:44:49
 * @see 
 */
public class ConfigModel implements Serializable{

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 目录/路径
	 */
	private String dir;
	
	/**
	 * 值
	 */
	private String value;
	
	/**
	 * 版本
	 */
	private String version;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 子模型
	 */
	private ConfigModel[] subs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ConfigModel[] getSubs() {
		return subs;
	}

	public void setSubs(ConfigModel[] subs) {
		this.subs = subs;
	}
	
	
	
}

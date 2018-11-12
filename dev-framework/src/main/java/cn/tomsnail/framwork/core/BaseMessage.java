package cn.tomsnail.framwork.core;

import java.io.Serializable;

import cn.tomsnail.util.configfile.ConfigHelp;

public class BaseMessage implements Serializable{


	private int status;
	
	private String msg;

	public int status() {
		return status;
	}

	public String msg() {
		return msg;
	}

	

	public BaseMessage(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public BaseMessage() {
	}
	
	public static BaseMessage build(int status,String key,String defaultValue){
		return new BaseMessage(status, ConfigHelp.getInstance("message").getLocalConfig(key, defaultValue));
	}
	
	public static BaseMessage build(int status,String msg){
		return new BaseMessage(status, msg);
	}
	
}
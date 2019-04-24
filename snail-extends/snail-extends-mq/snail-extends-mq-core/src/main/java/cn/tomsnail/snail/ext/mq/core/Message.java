package cn.tomsnail.snail.ext.mq.core;

import java.io.Serializable;

/**
 *        消息模型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月17日 下午1:12:39
 * @see 
 */
public class Message implements Serializable{

	/**
	 * uuid
	 */
	private String uuid;
	
	/**
	 * 消息对象
	 */
	private Object message;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Message(Object message) {
		this.message = message;
	}
	public Message() {
	}
	
	
}

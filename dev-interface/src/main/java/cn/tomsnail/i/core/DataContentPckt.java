package cn.tomsnail.i.core;

import java.io.Serializable;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *        数据内容包
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午2:26:19
 * @see 
 */
public class DataContentPckt implements Serializable{
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private static final long serialVersionUID = -1367412585364190883L;

	/**
	 * uuid
	 */
	private String uuid;
	
	/**
	 * 数据包发送者
	 */
	private String dataSender;
	
	/**
	 * 数据包接收者
	 */
	private String dataReceiver;
	
	/**
	 * 时间
	 */
	private String timeStr;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 内容发送者
	 */
	private String contentSender;
	
	/**
	 * 内容接收者
	 */
	private String contentReceiver;
	
	/**
	 * 内容时间
	 */
	private String contentTimeStr;
	
	/**
	 * 是否需要确认
	 */
	private boolean isComfired;
	
	/**
	 * 是否要重发
	 */
	private boolean isRepeatd;
	
	/**
	 * 重发次数
	 */
	private int repeatNumber;
	
	/**
	 * 规则编号
	 */
	private int ruleId;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDataSender() {
		return dataSender;
	}

	public void setDataSender(String dataSender) {
		this.dataSender = dataSender;
	}

	public String getDataReceiver() {
		return dataReceiver;
	}

	public void setDataReceiver(String dataReceiver) {
		this.dataReceiver = dataReceiver;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentSender() {
		return contentSender;
	}

	public void setContentSender(String contentSender) {
		this.contentSender = contentSender;
	}

	public String getContentReceiver() {
		return contentReceiver;
	}

	public void setContentReceiver(String contentReceiver) {
		this.contentReceiver = contentReceiver;
	}

	public String getContentTimeStr() {
		return contentTimeStr;
	}

	public void setContentTimeStr(String contentTimeStr) {
		this.contentTimeStr = contentTimeStr;
	}

	public boolean isComfired() {
		return isComfired;
	}

	public void setComfired(boolean isComfired) {
		this.isComfired = isComfired;
	}

	public boolean isRepeatd() {
		return isRepeatd;
	}

	public void setRepeatd(boolean isRepeatd) {
		this.isRepeatd = isRepeatd;
	}

	public int getRepeatNumber() {
		return repeatNumber;
	}

	public void setRepeatNumber(int repeatNumber) {
		this.repeatNumber = repeatNumber;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	
	@Override
	public String toString(){
		try {
			return OBJECT_MAPPER.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

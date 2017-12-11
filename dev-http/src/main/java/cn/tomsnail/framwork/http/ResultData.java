package cn.tomsnail.framwork.http;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装返回数据对象
 * Title: ResultData.java    
 * Description: TODO
 */
public class ResultData<T> implements java.io.Serializable {
	
	private static final long serialVersionUID = 4917480918640310535L;
    private String command = "";
    private String sequenceID = "";
    private String fingerprint = "";
    private T body;
    private String status = CommonMessage.SUCCESS;//消息状态
    private String msg = "操作成功";//消息内容
    private String code = "";
    private String msgCode = "";
    private String errorMsg;
    
	public ResultData() {
	}
	
	public String getCommand() {
		return command+"Resp";
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getSequenceID() {
		return sequenceID;
	}

	public void setSequenceID(String sequenceID) {
		this.sequenceID = sequenceID;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public static ResultData<Map<String,Object>> createMapResult(String key,Object v){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(key, v);
		ResultData<Map<String,Object>> resultData = new ResultData<Map<String,Object>>();
		resultData.setBody(map);
		return resultData;
	}
	
	
	public ResultData<Map<String,Object>> result(String key,Object v){
		Map<String,Object> map = null;
		if(body==null){
			map = new HashMap<String,Object>();
			body = (T) map;
		}else{
			map = (Map<String, Object>) body;
		}
		map.put(key, v);
		return (ResultData<Map<String, Object>>) this;
	}
}

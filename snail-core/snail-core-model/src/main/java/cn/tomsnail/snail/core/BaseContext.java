package cn.tomsnail.snail.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求上下文数据
 * @author yangsong
 *
 */
public class BaseContext {
	
	public static final String USER_UUID = "USER_UUID";
	
	public static final String SYSTEM_CODE = "SYSTEM_CODE";
	
	private String contextId;
	
	private String timestamp;
	
	private Map<String,String> context = new HashMap<String,String>();
	
	private String info;

	public Map<String, String> getContext() {
		if(context==null){
			context = new HashMap<String,String>();
		}
		return context;
	}

	public void setContext(Map<String, String> context) {
		this.context = context;
	}

	public String getContextId() {
		return contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public BaseContext addMapValue(String key,String value){
		if(context==null){
			context = new HashMap<String,String>();
		}
		context.put(key, value);
		return this;
	}
}

package cn.tomsnail.framwork.core;

import java.util.Map;

/**
 * 请求上下文数据
 * @author yangsong
 *
 */
public class BaseContext {
	
	private String contextId;
	
	private String timestamp;
	
	private Map<String,String> context;
	
	private String info;

	public Map<String, String> getContext() {
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
}

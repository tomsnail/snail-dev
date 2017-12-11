package cn.tomsnail.http.client.core;

import java.util.HashMap;
import java.util.Map;

/**
 *        返回
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午1:38:19
 * @see 
 */
public class Response<T> {
	
	public static final int ERROR = -1;

	private String uri;
	
	private String host;
	
	private Map<String,String> headers = new HashMap<String, String>();

	private T body;
	
	private StringBuffer origStr;
	
	private int status = 0;
	
	
	public Response(){
		
	}
	
	public Response(T t){
		this.body = t;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public StringBuffer getOrigStr() {
		return origStr;
	}

	public void setOrigStr(StringBuffer origStr) {
		this.origStr = origStr;
	}
	
	public void cloneObject(Response<T> response){
		this.setOrigStr(response.getOrigStr());
		this.setStatus(response.getStatus());
		this.setUri(response.getUri());
		this.setHeaders(response.getHeaders());
		this.setHost(response.getHost());
	}
	
}

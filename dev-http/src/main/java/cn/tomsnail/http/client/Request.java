package cn.tomsnail.http.client;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 *        请求
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午1:38:08
 * @see 
 */
public class Request<T> implements Cloneable{
	
	public static final int POST = 1;
	
	public static final int GET = 2;
	
	public static final int DEL = 3;
	
	public static final int PUT = 4;
	
	private String uri;
	
	private String host;
	
	private int method = Request.POST;
	
	/**
	 * 请求头
	 */
	private Map<String,String> headers;

	private T body;

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
	
	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public boolean isValid(){
		if(StringUtils.isBlank(this.uri)){
			return false;
		}
		return true;
	}

	@Override
	public Request clone() throws CloneNotSupportedException {
		return (Request) super.clone();
	} 
	
	public static void main(String[] args) throws Exception {
		Request request = new Request();
		request.setUri("1243");
		Request request1 = request.clone();
		System.out.println(request);
		System.out.println(request.getUri());

		System.out.println(request1);
		System.out.println(request1.getUri());

	}

}

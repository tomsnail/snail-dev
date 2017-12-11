package cn.tomsnail.http.client.exception;

/**
 *        http请求异常
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午2:03:29
 * @see 
 */
public class HttpRequestException extends Exception{

	private static final long serialVersionUID = -1296793362476849373L;

	public HttpRequestException(String e){
		super(e);
	}
	
}

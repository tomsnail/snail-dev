package cn.tomsnail.snail.core.util.http.client;

import java.io.UnsupportedEncodingException;
import java.util.Map;


//import cn.tomsnail.framwork.http.RequestData;
//import cn.tomsnail.framwork.http.ResultData;
//import cn.tomsnail.http.client.okhttp.OkhttpRequest;



/**
 *  默认请求      
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午2:14:23
 * @see 
 */
public class DefaultRequest {
	
	private static HttpClientFactory httpClientFactory = HttpClientFactory.initialize();

	public static String request(Request<Map<String,String>> request) {
		if(request==null||!request.isValid()){
			return null;
		}
		try {
			return new String(httpClientFactory.post(request).getOrigStr().toString().getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String requestNoZip(Request<Map<String,String>> request) {
		if(request==null||!request.isValid()){
			return null;
		}
		try {
			return new String(httpClientFactory.postNZ(request).getOrigStr().toString().getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String requestForMap(Request<Map<String,Object>> request) {
		if(request==null||!request.isValid()){
			return null;
		}
		return httpClientFactory.post(request).getOrigStr().toString();
	}
	
//	public static ResultData<Map<String, String>> requestFramework(Map<String,String> paramMap,String url) throws Exception {
//		Request<RequestData<Map<String, String>>> request = new Request<RequestData<Map<String, String>>>();
//		RequestData<Map<String, String>> requestData = new RequestData<Map<String, String>>();
//		requestData.setBody(paramMap);
//		request.setBody(requestData);
//		request.setUri(url);
//		if(request==null||!request.isValid()){
//			return null;
//		}
//		Response response =  httpClientFactory.post(request);
//		System.out.println("response:"+response.getOrigStr());
//		return HttpClientFactory.OBJECT_MAPPER.readValue(response.getOrigStr().toString(), ResultData.class);
//	}
	
	
	
}

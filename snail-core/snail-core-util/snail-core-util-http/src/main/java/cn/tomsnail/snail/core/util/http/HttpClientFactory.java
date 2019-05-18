package cn.tomsnail.snail.core.util.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *        http 
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年1月13日 下午2:56:19
 * @see 
 */
public class HttpClientFactory {
	
	private static HttpClientFactory factory;
	
	private static final byte[] LOCK_BYTE = new byte[1];
	
	private static final Logger logger = LoggerFactory.getLogger(HttpClientFactory.class);

	private static HttpParams httpParams;
	private static ClientConnectionManager connectionManager;
	/**
	 * 最大连接数
	 */
	public final static int MAX_TOTAL_CONNECTIONS = 100;
	/**
	 * 获取连接的最大等待时间
	 */
	public final static int WAIT_TIMEOUT = 60000;
	/**
	 * 每个路由最大连接数
	 */
	public final static int MAX_ROUTE_CONNECTIONS = 50;
	/**
	 * 连接超时时间
	 */
	public final static int CONNECT_TIMEOUT = 10000;
	/**
	 * 读取超时时间
	 */
	public final static int READ_TIMEOUT = 10000;

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年1月13日 下午2:56:37
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	private static void initialize(int MAX_TOTAL_CONNECTIONS, int WAIT_TIMEOUT,
			int MAX_ROUTE_CONNECTIONS, int CONNECT_TIMEOUT, int READ_TIMEOUT) {
		httpParams = new BasicHttpParams();
		// 设置最大连接数
		ConnManagerParams.setMaxTotalConnections(httpParams,
				MAX_TOTAL_CONNECTIONS);
		// 设置获取连接的最大等待时间
		ConnManagerParams.setTimeout(httpParams, WAIT_TIMEOUT);
		// 设置每个路由最大连接数
		ConnPerRouteBean connPerRoute = new ConnPerRouteBean(
				MAX_ROUTE_CONNECTIONS);
		ConnManagerParams.setMaxConnectionsPerRoute(httpParams, connPerRoute);
		// 设置连接超时时间
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
		// 设置读取超时时间
		HttpConnectionParams.setSoTimeout(httpParams, READ_TIMEOUT);

		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		registry.register(new Scheme("https", 443, SSLSocketFactory
				.getSocketFactory()));
		connectionManager = new ThreadSafeClientConnManager(httpParams,
				registry);
	}

	private static HttpClient getHttpClient() {
		return new DefaultHttpClient(connectionManager, httpParams);
	}

	private static HttpClientFactory instance;

	public HttpClientFactory() {
		initialize();
	}
	
	public HttpClientFactory(int MAX_TOTAL_CONNECTIONS,int MAX_ROUTE_CONNECTIONS,int WAIT_TIMEOUT) {
		initialize(MAX_TOTAL_CONNECTIONS,MAX_ROUTE_CONNECTIONS,WAIT_TIMEOUT);
	}
	
	public HttpClientFactory(int MAX_TOTAL_CONNECTIONS, int WAIT_TIMEOUT,
			int MAX_ROUTE_CONNECTIONS, int CONNECT_TIMEOUT, int READ_TIMEOUT) {
		initialize(MAX_TOTAL_CONNECTIONS,WAIT_TIMEOUT,MAX_ROUTE_CONNECTIONS,CONNECT_TIMEOUT,READ_TIMEOUT);
	}

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年1月13日 下午2:59:19
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static  HttpClientFactory initialize() {
			return  initialize(MAX_TOTAL_CONNECTIONS,WAIT_TIMEOUT,MAX_ROUTE_CONNECTIONS);
	}
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年1月13日 下午2:59:22
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static  HttpClientFactory initialize(int MAX_TOTAL_CONNECTIONS,int MAX_ROUTE_CONNECTIONS,int WAIT_TIMEOUT) {
		synchronized (LOCK_BYTE) {
			if(factory==null)
				factory = new HttpClientFactory(MAX_TOTAL_CONNECTIONS,WAIT_TIMEOUT,MAX_ROUTE_CONNECTIONS,CONNECT_TIMEOUT,READ_TIMEOUT);
			return factory;
		}
	}
	
	
	

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年1月13日 下午2:59:26
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String post(String host, String url, StringBuffer params) {
		HttpClient httpClient = getHttpClient();
		httpClient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = new HttpPost(url); // 设置响应头信息
		httpost.addHeader("Connection", "keep-alive");
		httpost.addHeader("Accept", "*/*");
		httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpost.addHeader("Host", host);
		httpost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpost.addHeader("Cache-Control", "max-age=0");
		httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
		String result = "";
		try {
			StringEntity entity = new StringEntity(params.toString(), "UTF-8");
			httpost.setEntity(entity);
			HttpResponse response = null;
			try {
				response = httpClient.execute(httpost);
				
			} catch (Exception e) {
				e.printStackTrace();
				try {
					response = httpClient.execute(httpost);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(response != null){
				int status = response.getStatusLine().getStatusCode(); 
				if (status >= 200 && status < 300) {  
					result = EntityUtils.toString(response.getEntity(), "UTF-8");
					//logger.info("[HttpSendUtil][sendPost complate], [result:" + result + "], [" + DateUtil.getLogDate() + "]");
				}else{
					//logger.info("[HttpSendUtil][sendPost complate], [status:" + status + "], [" + DateUtil.getLogDate() + "]");
				}
			}
		} catch (Exception e) {
			//logger.error("[HttpSendUtil][sendPost sendPost fail], [url:" + url + ", param:" + params.toString() + "], [" + DateUtil.getLogDate() + "]" + e);
			e.printStackTrace();
		}
		finally {
			if (httpost != null) {
				httpost.abort();
			}
		}
		return result;
	}

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年1月13日 下午2:59:31
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String post(String url, StringBuffer params) {
		 	String returnStr = null;  
	        //参数检测  
	        if(url==null||"".equals(url))  
	        {  
	            return returnStr;  
	        }  
	        HttpPost httpPost = new HttpPost(url);  
	        try {  
	            List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
	            nvps.add(new BasicNameValuePair("jsonstr", params.toString()));  
	            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));  
	            HttpResponse response = getHttpClient().execute(httpPost);  
	            int status = response.getStatusLine().getStatusCode();  
	            if (status >= 200 && status < 300) {  
	                HttpEntity entity = response.getEntity();  
	                String resopnse="";  
	                if(entity != null)  
	                {  
	                    resopnse=EntityUtils.toString(entity,"utf-8");  
	                }  
	                return entity != null ? resopnse : null;  
	            } else {  
	                //HttpEntity entity = response.getEntity();
	                httpPost.abort();  
	                logger.error(" status"+status+" url="+url+" jsonstr="+params.toString());  
	                throw new ClientProtocolException("Unexpected response status: " + status);  
	            }  
	        } catch (Exception e) {  
	            httpPost.abort();  
	            logger.error(" Exception"+e.toString()+" url="+url+" jsonstr="+params.toString());  
	        }   
	        return returnStr;  
	}
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年1月13日 下午2:59:36
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String get(StringBuffer url) {
		String returnStr = "";
		 try {
			HttpClient client = getHttpClient();  
			  
			 HttpGet get = new HttpGet(url.toString());  
			 HttpResponse response = client.execute(get);  
			 if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
			     HttpEntity entity = response.getEntity();  
			     String resopnse="";  
	                if(entity != null)  
	                {  
	                    resopnse=EntityUtils.toString(entity,"utf-8");  
	                }  
	                return entity != null ? resopnse : null;  
			 }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
         return returnStr;
}

	private static HttpClientFactory httpClientFactory = HttpClientFactory.initialize();
	
	public static void main(String[] args) {
		System.out.println(httpClientFactory.post("","", new StringBuffer("http://www.baidu.com/s?wd=123")));
	}

}

package cn.tomsnail.http.client;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import cn.tomsnail.http.client.OkhttpUtil;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request.Builder;


public class OkhttpRequest {

	public static String get(String uri){
		Response response = null;
		try {
			OkHttpClient okHttpClient = OkhttpUtil.defaultPoolClient();
			String url = uri;
			Request request = new Request.Builder().url(url).build();
			Call call = okHttpClient.newCall(request);
			response = call.execute();
			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(response!=null){
				response.close();
			}
		}
		return null;
	}
	
	
	public static String get(String uri,long timeout){
		Response response = null;
		try {
			OkHttpClient okHttpClient = OkhttpUtil.defaultPoolClient(timeout);
			String url = uri;
			Request request = new Request.Builder().url(url).build();
			Call call = okHttpClient.newCall(request);
			response = call.execute();
			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(response!=null){
				response.close();
			}
		}
		return null;
	}
	
	public static String post(String uri, Map<String, String> paramsMap){
		Response response = null;
		try {
			 //处理参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
     
            //生成参数
            String params = tempParams.toString();
            //创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), params);
            //创建一个请求
			OkHttpClient okHttpClient = OkhttpUtil.defaultPoolClient();

            final Request request = new Request.Builder().url(uri).post(body).build();
            //创建一个Call
            final Call call = okHttpClient.newCall(request);
            //执行请求
            response = call.execute();
            //请求执行成功
            if (response.isSuccessful()) {
              return response.body().string();
            }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(response!=null){
				response.close();
			}
		}
		return null;
	}
	
	public static String post(String uri,String jsonStr){
		Response response = null;
		try {
            //创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonStr);
            //创建一个请求
			OkHttpClient okHttpClient = OkhttpUtil.defaultPoolClient();

            final Request request = new Request.Builder().url(uri).post(body).build();
            //创建一个Call
            final Call call = okHttpClient.newCall(request);
            //执行请求
            response = call.execute();
            //请求执行成功
            if (response.isSuccessful()) {
              return response.body().string();
            }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(response!=null){
				response.close();
			}
		}
		return null;
	}
	
	
	public static String post(String uri, String jsonStr,Map<String,String> headers){
		Response response = null;
		try {

            //创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonStr);
            //创建一个请求
			OkHttpClient okHttpClient = OkhttpUtil.defaultPoolClient();
			
			Builder builder = new Request.Builder();
			
			if(headers!=null&&!headers.isEmpty()){
				 for (String key : headers.keySet()) {
					 builder.addHeader(key, headers.get(key));
		         }
			}
			
            final Request request = builder.url(uri).post(body).build();
            //创建一个Call
            final Call call = okHttpClient.newCall(request);
            //执行请求
            response = call.execute();
            //请求执行成功
            if (response.isSuccessful()) {
              return response.body().string();
            }else{
            	System.out.println(response.message());
            }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(response!=null){
				response.close();
			}
		}
		return null;
	}
	
	public static String postHttps(String uri, String jsonStr,Map<String,String> headers){
		Response response = null;
		try {

            //创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonStr);
            //创建一个请求
			OkHttpClient okHttpClient = OkhttpUtil.defaultPoolClient();
			
			okHttpClient = OkhttpUtil.getSSLOkHttpClient(okHttpClient);

			Builder builder = new Request.Builder();
			
			if(headers!=null&&!headers.isEmpty()){
				 for (String key : headers.keySet()) {
					 builder.addHeader(key, headers.get(key));
		         }
			}
			
            final Request request = builder.url(uri).post(body).build();
            //创建一个Call
            final Call call = okHttpClient.newCall(request);
            //执行请求
            response = call.execute();
            //请求执行成功
            if (response.isSuccessful()) {
              return response.body().string();
            }else{
            	System.out.println(response.message());
            }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(response!=null){
				response.close();
			}
		}
		return null;
	}
	
	public static String postHttps(String uri, String jsonStr){
		Response response = null;
		try {

            //创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonStr);
            //创建一个请求
			OkHttpClient okHttpClient = OkhttpUtil.defaultPoolClient();
			
			okHttpClient = OkhttpUtil.getSSLOkHttpClient(okHttpClient);

			Builder builder = new Request.Builder();
			
            final Request request = builder.url(uri).post(body).build();
            //创建一个Call
            final Call call = okHttpClient.newCall(request);
            //执行请求
            response = call.execute();
            //请求执行成功
            if (response.isSuccessful()) {
              return response.body().string();
            }else{
            	System.out.println(response.message());
            }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(response!=null){
				response.close();
			}
		}
		return null;
	}
	

	
}

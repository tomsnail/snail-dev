package cn.tomsnail.snail.e3.aliyun.ocr.providers.aliyun;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;

public class HttpApiClient extends ApacheHttpClient {

	static HttpApiClient instance = new HttpApiClient();

	public static HttpApiClient getInstance() {
		return instance;
	}

	public void init(String scheme, String domain, String appKey,String appSecret) {
		
		if(StringUtils.isAnyBlank(scheme,domain,appKey,appSecret)){
			throw new NullPointerException();
		}
		
		HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
		httpParam.setAppKey(appKey);
		httpParam.setAppSecret(appSecret);
		httpParam.setHost(domain);
		if ("http".equalsIgnoreCase(scheme)) {
			initHttp(httpParam);
		}
		if ("https".equalsIgnoreCase(scheme)) {
			initHttps(httpParam);
		}
	}

	private void initHttp(HttpClientBuilderParams httpClientBuilderParams) {
		httpClientBuilderParams.setScheme(Scheme.HTTP);
		super.init(httpClientBuilderParams);
	}

	private void initHttps(HttpClientBuilderParams httpClientBuilderParams) {
		X509TrustManager xtm = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				X509Certificate[] x509Certificates = new X509Certificate[0];
				return x509Certificates;
			}
		};

		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, new TrustManager[] { xtm },
					new SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		}
		HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		httpClientBuilderParams.setSslSocketFactory(sslContext
				.getSocketFactory());
		httpClientBuilderParams.setX509TrustManager(xtm);
		httpClientBuilderParams.setHostnameVerifier(DO_NOT_VERIFY);
		httpClientBuilderParams.setScheme(Scheme.HTTPS);
		super.init(httpClientBuilderParams);
	}

	
	public void asyncPost(String path,byte[] body, ApiCallback callback) {
		
		if(StringUtils.isBlank(path)||body==null||body.length==0||callback==null){
			throw new NullPointerException();
		}
		
	    ApiRequest request = new ApiRequest(HttpMethod.POST_BODY , path, body);
	    sendAsyncRequest(request , callback);
	}
	
	
	public ApiResponse syncPost(String path,byte[] body) {
		
		if(StringUtils.isBlank(path)||body==null||body.length==0){
			throw new NullPointerException();
		}
		
	    ApiRequest request = new ApiRequest(HttpMethod.POST_BODY , path, body);
	    return sendSyncRequest(request);
	}
	
	
	public static String getResultString(ApiResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("Response from backend server").append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        result.append("ResultCode:").append(SdkConstant.CLOUDAPI_LF).append(response.getCode()).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        if(response.getCode() != 200){
            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        }

        result.append("ResultBody:").append(SdkConstant.CLOUDAPI_LF).append(new String(response.getBody() , SdkConstant.CLOUDAPI_ENCODING));

        return result.toString();
    }

}

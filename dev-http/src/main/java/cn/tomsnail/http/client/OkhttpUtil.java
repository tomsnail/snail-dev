package cn.tomsnail.http.client;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

public class OkhttpUtil {

	private static final ConnectionPool connectionPool = new ConnectionPool();
	private static final Dispatcher dispatcher = new Dispatcher();

	public static OkHttpClient defaultPoolClient() {
		return new OkHttpClient.Builder().connectionPool(connectionPool)// .cache(new
																		// Cache(new
																		// File("test"),
																		// 100000))
				.build();
	}
	
	
	public static OkHttpClient defaultPoolClient(long timeout) {
		return new OkHttpClient.Builder().connectionPool(connectionPool)// .cache(new
																		// Cache(new
																		// File("test"),
																		// 100000))
				.connectTimeout(timeout, TimeUnit.SECONDS).readTimeout(timeout, TimeUnit.SECONDS).writeTimeout(timeout, TimeUnit.SECONDS).build();
	}
	
	public static OkHttpClient numPoolClient(int number) {
		return new OkHttpClient.Builder().readTimeout(1, TimeUnit.MINUTES).connectionPool(new ConnectionPool(number,5, TimeUnit.MINUTES))// .cache(new
																		// Cache(new
																		// File("test"),
																		// 100000))
				.build();
	}

	public static OkHttpClient defaultClient() {
		return new OkHttpClient.Builder().build();
	}

	public static OkHttpClient getSSLOkHttpClient(OkHttpClient okHttpClient)
			throws Exception {
		X509TrustManager xtm = new X509TrustManager() {

			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException {

			}

			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException {

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
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		OkHttpClient okHttpClient0 = okHttpClient.newBuilder()
				.sslSocketFactory(sslContext.getSocketFactory())
				.hostnameVerifier(DO_NOT_VERIFY).build();
		return okHttpClient0;
	}

	public static OkHttpClient getSSLOkHttpClient(OkHttpClient okHttpClient,
			List<Protocol> protocols) throws Exception {
		X509TrustManager xtm = new X509TrustManager() {

			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException {

			}

			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException {

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
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		OkHttpClient okHttpClient0 = okHttpClient.newBuilder()
				.sslSocketFactory(sslContext.getSocketFactory())
				.hostnameVerifier(DO_NOT_VERIFY).protocols(protocols).build();
		return okHttpClient0;
	}
}

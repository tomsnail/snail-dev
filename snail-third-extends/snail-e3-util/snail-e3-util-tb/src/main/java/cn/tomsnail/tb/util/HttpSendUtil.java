package cn.tomsnail.tb.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;




/**
 * @ClassName HttpSendUtil
 * @Description 调用PC端接口实现类
 * @author zhanggeliang
 * @date 2015-11-18
 */
public class HttpSendUtil {

	

	private static  String sendPost(String url, String param) {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = new HttpPost(url); // 设置响应头信息
		httpost.addHeader("Connection", "keep-alive");
		httpost.addHeader("Accept", "*/*");
		httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpost.addHeader("Cache-Control", "max-age=0");
		httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
		
		String result = "";
		try {
			StringEntity entity = new StringEntity(param, "UTF-8");
			httpost.setEntity(entity);
			HttpResponse response = null;
			try {
				response = client.execute(httpost);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					response = client.execute(httpost);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(response != null){
				result = EntityUtils.toString(response.getEntity(), "UTF-8");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			if (httpost != null) {
				httpost.abort();
			}
		}
		return result;
	}
	
}

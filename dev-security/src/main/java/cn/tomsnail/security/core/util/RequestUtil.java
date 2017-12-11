package cn.tomsnail.security.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.framwork.http.RequestData;
import net.sf.json.JSONArray;

  
/**        
 * Title: RequestUtil.java    
 * Description: web接口请求解析工具类
 * @author zhanghl       
 * @date 2015年12月3日 下午5:58:16    
 */      
public class RequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);
	
	 public static String getBodyString(BufferedReader br) {
		  String inputLine;
		       String str = "";
		     try {
		       while ((inputLine = br.readLine()) != null) {
		        str += inputLine;
		       }
		       br.close();
		     } catch (IOException e) {
		    	 logger.error("", e);
		     }
		     return str;
		 }
}

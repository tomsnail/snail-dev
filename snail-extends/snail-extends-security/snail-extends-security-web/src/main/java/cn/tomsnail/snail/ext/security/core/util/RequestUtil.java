package cn.tomsnail.snail.ext.security.core.util;

import java.io.BufferedReader;
import java.io.IOException;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



  
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
		       StringBuffer str = new StringBuffer();
		     try {
		       while ((inputLine = br.readLine()) != null) {
		        str.append(inputLine);
		       }
		       br.close();
		     } catch (IOException e) {
		    	 logger.error("", e);
		     }
		     return str.toString();
		 }
}

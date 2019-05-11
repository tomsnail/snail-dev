package cn.tomsnail.snail.core.util.http;

import javax.servlet.http.HttpServletRequest;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年9月12日 下午4:26:18
	* @see 
	*/     
public class RemoteHostUtil {
	
	

	  
	   /**
		*        获取http请求IPv4地址
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月12日 下午4:26:10
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public String getRemoteHost(HttpServletRequest request) {
		
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
}

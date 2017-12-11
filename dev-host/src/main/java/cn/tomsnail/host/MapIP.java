package cn.tomsnail.host;

import org.apache.commons.lang.StringUtils;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月28日 下午1:12:17
 * @see 
 */
public class MapIP {

	public static String ip = getIP();
	
	public static int port = getPort();
	
	public static String getIP(){
		String ip = System.getenv("DEV_MAP_IP");
		if(StringUtils.isBlank(ip)){
			return null;

		}
		return ip;
	}
	
	public static int getPort(){
		String port = System.getenv("DEV_MAP_PORT");
		if(StringUtils.isBlank(ip)){
			return 8080;
		}
		try {
			return Integer.valueOf(port);
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		}
		return 8080;

	}
	
}

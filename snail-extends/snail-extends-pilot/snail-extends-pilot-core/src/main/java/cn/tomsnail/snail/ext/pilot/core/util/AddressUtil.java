package cn.tomsnail.snail.ext.pilot.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月12日 下午3:48:38
 * @see 
 */
public class AddressUtil {

	public static String getLocalAddr(){
		String _serviceIP = "";
		try {
			InetAddress[] adds =  InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
			if(adds==null){
				_serviceIP = InetAddress.getLocalHost().getHostName();
			}else{
				_serviceIP = adds[0].getHostAddress();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return _serviceIP;
	}
}

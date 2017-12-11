package cn.tomsnail.host;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *        应用名称类，取startup.sh的EXE_NAME属性
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月26日 上午10:54:31
 * @see 
 */
public class AppName {

	/**
	 * 
	 */
	public static String AppName = getAppName();

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月26日 上午10:54:36
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static String getAppName(String defaultAppName) {
		String startfile = System.getProperty("user.dir") + File.separator
				+ "startup.sh";
		String appName = defaultAppName;
		try {
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					startfile));
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("EXE_NAME=")) {
					appName = line.split("EXE_NAME=")[1];
				}
			}
			if(appName!=defaultAppName){
				appName = appName + "-" +HostIP.HOST_IP;
			}
			isr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appName;

	}

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月26日 上午10:54:39
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static String getAppName() {
		String appName = "Application_" + System.currentTimeMillis();
		return getAppName(appName);

	}
}

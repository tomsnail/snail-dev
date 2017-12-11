package cn.tomsnail.pilot.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月6日 上午10:42:28
 * @see 
 */
public class ExeBashUtil {

	private  static final Logger logger = LoggerFactory.getLogger(ExeBashUtil.class);

	
	public static void startup(String startFile){
		try {
			
			logger.info("startup : " + startFile);
			String shCmd = startFile;
			logger.info("startup file : " + shCmd);

					
			String[] cmds = new String[3];
			cmds[0] = "/bin/sh";
			cmds[1] = "-c";
			cmds[2] = shCmd;
			//cmds[3] = ">/dev/null";
			
			String os = System.getProperty("os.name");
			if (!os.toUpperCase().contains("WIN")) {
				Runtime.getRuntime().exec("chmod u+x " + shCmd);
			}
			//
			Process p = Runtime.getRuntime().exec(cmds);
			logger.info(cmds.toString());
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				logger.error(e.getMessage());

				e.printStackTrace();
			}
			
			logger.info("startup " + startFile + " OK! ");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void shutdown(String stopFile){
		try {
			logger.info("shutdown : " + stopFile);
			String shCmd = stopFile;
			logger.info("shutdown file : " + shCmd);
			String[] cmds = new String[3];
			cmds[0] = "/bin/sh";
			cmds[1] = "-c";
			cmds[2] = shCmd;
			String os = System.getProperty("os.name");
			if (!os.toUpperCase().contains("WIN")) {
				Runtime.getRuntime().exec("chmod u+x " + shCmd);
			}
			Process p = Runtime.getRuntime().exec(cmds);
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				logger.error(e.getMessage());

				e.printStackTrace();
			}
			logger.info("shutdown " + stopFile + " OK! ");
		} catch (Exception e) {
			logger.error(e.getMessage());

			e.printStackTrace();
		}
	}
	
	public static boolean check(String name) {
		try {
			if (isRuning(name) >= 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	
	public static int isRuning(String name) {
		int isRuning = 0;
		try {
			String cmd = "ps -ef | grep -v grep|grep java | grep " + name + " | awk '{print $2}' ";
			String[] cmds = new String[3];
			cmds[0] = "/bin/sh";
			cmds[1] = "-c";
			cmds[2] = cmd;
			Process p = Runtime.getRuntime().exec(cmds);
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				logger.info("checkStatus " + name + " info : " + line);
				if (line.length() > 1) {
					isRuning = 1;
				}
			}
			logger.info("checkStatus " + name + " : " + isRuning);
			logger.info("checkStatus " + name + " OK! ");
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return isRuning;
	}
	
}

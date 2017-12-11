package cn.tomsnail.pilot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 配置文件工具类
 * */
public class ConfigHelp {
	
	
	private String rootPath = "";
	
	private  Properties properties;
	
	private  String fileName;
	
	private static final HashMap<String,ConfigHelp> ConfigHelpMap = new HashMap<String, ConfigHelp>();
	
	private static String PATH="";
	
	private static final Lock LOCK = new ReentrantLock();
	
	
	@SuppressWarnings("rawtypes")
	public static void initPath(Class clazz){
		if(clazz==null){
			return;
		}
		PATH = ProgramPathHelper.getProgramPath(clazz);
		
	}
	
	private ConfigHelp(String fileName){
		this.fileName = fileName;
	}
	
	public static ConfigHelp getInstance(String fileName){
		ConfigHelp configHelp = null;
		LOCK.lock();
		if(ConfigHelpMap.containsKey(fileName)){
			configHelp = ConfigHelpMap.get(fileName);
		}else{
			configHelp = new ConfigHelp(fileName);
			ConfigHelpMap.put(fileName, configHelp);
		}
		LOCK.unlock();
		return configHelp;
	}
	
	
	/**
	 * 读取本机的配置文件,不去判断远程模式及工程模式
	 * @param key
	 * 参数名
	 * @param defaultValue
	 * 默认值
	 * @return 参数值
	 * */
	public String getLocalConfig(String key,String defaultValue){
		return getProperties(key,defaultValue);
	}

	

	
	/**
	 * 读取配置文件
	 * */
	private  String getProperties(String key,String defaultValue){
		if(properties==null){
			try{
				String path = "";
				if(PATH.equals("")){
					path = ProgramPathHelper.getProgramPath() +System.getProperty("file.separator")+rootPath+System.getProperty("file.separator")+fileName+".properties";
				}else{
					path = PATH +System.getProperty("file.separator")+rootPath+System.getProperty("file.separator")+fileName+".properties";

				}
				URL url = null;
				File file = new File(path);
				try {
					if (file.exists()) {

						url = file.toURI().toURL();
					} else {
						url = new URL(path);
					}
				} catch (MalformedURLException e) {
					url = SystemResourceUtil.findAsResource(path);
				}
				if (null == url) {
					throw new FileNotFoundException(path);
				}
				properties =  new Properties(); 
				properties.load(new InputStreamReader(new FileInputStream(new File(url.toURI()))));
			}catch(Exception ex){
				Logger logger = LoggerFactory.getLogger(ConfigHelp.class);
				logger.warn("read configuration.properties while error", ex);
			}
		}
		try{
			String result = properties.getProperty(key);
			if(result!=null && !"".equals(result)){
				return result;
			}
		}catch(Exception ex){
			Logger logger = LoggerFactory.getLogger(ConfigHelp.class);
			logger.warn("return  configuration.properties value while error", ex);
		}
		return defaultValue;
	}
	
	
	public static void main(String[] args) {
		ConfigHelp configHelp = ConfigHelp.getInstance("name");
		System.out.println(configHelp.getLocalConfig("长沙", "1"));
		
	}
	

}

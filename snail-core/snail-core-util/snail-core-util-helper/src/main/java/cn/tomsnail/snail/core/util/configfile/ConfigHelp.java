package cn.tomsnail.snail.core.util.configfile;

import java.io.File;
import java.io.FileInputStream;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigHelp.class);

	
	private static final String DEFAULT_ROOT_CONFIG_PATH = "/opt/config";
	
	private  Properties properties;
	
	private  String fileName;
	
	private static final HashMap<String,ConfigHelp> ConfigHelpMap = new HashMap<String, ConfigHelp>();
	
	
	private static final Lock LOCK = new ReentrantLock();
	
	
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
				String _path = fileName+".properties";
				if(new File(DEFAULT_ROOT_CONFIG_PATH+"/"+_path).exists()){
					path = DEFAULT_ROOT_CONFIG_PATH+"/"+_path;
				}else{
					path =_path;
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
					//throw new FileNotFoundException(path);
					return defaultValue;
				}
				properties =  new Properties(); 
				properties.load(new InputStreamReader(new FileInputStream(new File(url.toURI()))));
			}catch(Exception ex){
				logger.warn(ex.getMessage());
			}
		}
		try{
			if(properties!=null){
				String result = properties.getProperty(key);
				if(result!=null && !"".equals(result)){
					return result;
				}
			}
		}catch(Exception ex){
			logger.warn(ex.getMessage());
		}
		return defaultValue;
	}
	
	
	public static void main(String[] args) {
		
	}
	

}

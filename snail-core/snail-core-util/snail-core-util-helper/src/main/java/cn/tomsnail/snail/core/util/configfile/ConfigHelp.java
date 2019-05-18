package cn.tomsnail.snail.core.util.configfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.tomsnail.snail.core.util.commons.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.snail.core.util.string.StringUtils;

import static cn.tomsnail.snail.core.util.configfile.TransferUtils.yml2Properties;


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

	public static ConfigHelp getInstance(){
		return getInstance("config");
	}

	public static ConfigHelp getInstance(String fileName){
		ConfigHelp configHelp = null;
		try{
			LOCK.lock();
			if(ConfigHelpMap.containsKey(fileName)){
				configHelp = ConfigHelpMap.get(fileName);
			}else{
				configHelp = new ConfigHelp(fileName);
				ConfigHelpMap.put(fileName, configHelp);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			LOCK.unlock();
		}


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
	
	public long getLocalConfig(String key,long defaultValue){
		String v = getProperties(key,"");
		if(StringUtils.isBlank(v)) {
			return defaultValue;
		}
		try {
			return Long.parseLong(v);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	
	public int getLocalConfig(String key,int defaultValue){
		String v = getProperties(key,"");
		if(StringUtils.isBlank(v)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(v);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	
	
	public boolean getLocalConfig(String key,boolean defaultValue){
		String v = getProperties(key,"");
		if(StringUtils.isBlank(v)) {
			return defaultValue;
		}
		try {
			return Boolean.parseBoolean(v);
		} catch (Exception e) {
			return defaultValue;
		}
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
					return getYamlProperties(key,defaultValue);
				}
				try(InputStreamReader is = new InputStreamReader(new FileInputStream(new File(url.toURI())))){
					properties =  new Properties();
					properties.load(is);
				}

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


	private  String getYamlProperties(String key,String defaultValue){
		if(properties==null){
			try{
				String path = "";
				String _path = fileName+".yml";
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

				List<String> proList = TransferUtils.yml2Properties(url.getPath());
				if(CollectionUtils.isEmpty(proList)){
					return defaultValue;
				}
				properties =  new Properties();
				for(String line:proList ){
					String lineKey = line.split("=")[0];
					String lineValue = line.replace(lineKey+"=","");
					properties.setProperty(lineKey,lineValue);
				}
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


}

package cn.tomsnail.snail.core.framework.spi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.configfile.SystemResourceUtil;


/**
 *        默认
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月5日 下午3:27:13
 * @see 
 */
public class DefaultSpiCoreContextHolder extends ASpiCoreContextHolder{
	
	private int flag = -1;
	
	private String path = "services.properties";
	
	private volatile Properties properties;
	
	public DefaultSpiCoreContextHolder(){
		isDefaultSPI();
	}
	
	public DefaultSpiCoreContextHolder(String path){
		this.path = path==null?this.path:path;
		isDefaultSPI();
	}

	@Override
	public String getSpiClass(String spi) {
		if(!isDefaultSPI()){
			return getProperties(spi,"");
		}
		return null;
	}

	@Override
	public boolean isDefaultSPI() {
		if(flag==-1){
			try {
				File file = new File(DefaultSpiCoreContextHolder.class.getClassLoader().getResource("").getPath()+path);
				if(file.exists()){
					flag = 1;
				}else{
					flag = 0;
				}
			} catch (Exception e) {
				flag = 0;
			}
			return isDefaultSPI();
		}
		else {
			return flag == 0;
		}
	}
	
	private  String getProperties(String key,String defaultValue){
		if(properties==null){
			synchronized (path) {
				if(properties==null){
					try{
						String path = DefaultSpiCoreContextHolder.class.getClassLoader().getResource("").getPath()+this.path;
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
						try(InputStreamReader is = new InputStreamReader(new FileInputStream(new File(url.toURI())),"UTF-8")){
							properties.load(is);
						}

					}catch(Exception ex){
						Logger logger = LoggerFactory.getLogger(ConfigHelp.class);
						logger.warn("read configuration.properties while error", ex);
					}
				}
			}
		}
		if(properties==null){
			return defaultValue;
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

	@Override
	public Object getSpiObject(String clazz, Object[] params,
			Class[] paramTypes) {
		try {
			Class _clazz = Class.forName(clazz);
			//Class[] _paramTypes = {String.class};
			Object[] _params = params; 
			Constructor con = _clazz.getConstructor(paramTypes); 
			return con.newInstance(params);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public Object getSpiObject(String clazz) {
		try {
			Class _clazz = Class.forName(clazz);
			return _clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getSpiObjectFromSpiObject(String spi,String defaultObj) {
		String className = super.getSpiClass(spi, defaultObj);
		return getSpiObject(className);
	}

}

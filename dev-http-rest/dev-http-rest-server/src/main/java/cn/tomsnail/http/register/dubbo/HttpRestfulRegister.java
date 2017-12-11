package cn.tomsnail.http.register.dubbo;


import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.framwork.spi.DefaultSpiCoreContextHolder;
import cn.tomsnail.framwork.spi.SpiCoreContextHolder;
import cn.tomsnail.http.register.annotation.HttpRestServiceDubbo;
import cn.tomsnail.http.register.core.IRegister;
import cn.tomsnail.http.register.core.IRegisterFactory;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.NotifyListener;
import com.alibaba.dubbo.registry.Registry;

/**
 *        dubbo httprest 扩展，dubbo加载时注册httprest信息
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 上午10:30:24
 * @see 
 */
public class HttpRestfulRegister implements Registry{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpRestfulRegister.class);
	
	private static IRegisterFactory registerFactory = getRegisterFactory() ;
	
	private static final byte[] lock = new byte[1];
	
	private static SpiCoreContextHolder spiCoreContextHolder ;
	
	private static final Map<String,IRegister> RegisterMap = new ConcurrentHashMap<String, IRegister>();
	
	private String address;
	
	public static IRegisterFactory getRegisterFactory(){
		spiCoreContextHolder = new DefaultSpiCoreContextHolder("dev-http-rest-server.properties");
		IRegisterFactory registerFactory =  (IRegisterFactory) spiCoreContextHolder.getSpiObjectFromSpiObject("server.RegisterFactory","cn.tomsnail.http.register.core.RegisterFactory");
		if(registerFactory==null){
			return null;
		}
		return registerFactory;
	}
		
	public HttpRestfulRegister(URL url){
		address = url.getAddress();
		if(registerFactory==null){
			return;
		}
		if(!RegisterMap.containsKey(address)){
			RegisterMap.put(address, registerFactory.getRegister(address));
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public URL getUrl() {
		return null;
	}

	@Override
	public boolean isAvailable() {
		return false;
	}

	@Override
	public List<URL> lookup(URL url) {
		return null;
	}

	@Override
	public void register(URL url) {
		Class clazz = null;
		
		try {
			clazz = Class.forName(url.getServiceInterface());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		if(clazz.isAnnotationPresent(HttpRestServiceDubbo.class)){
			HttpRestServiceDubbo httpRestServiceDubbo = (HttpRestServiceDubbo) clazz.getAnnotation(HttpRestServiceDubbo.class);
			try {
				if(httpRestServiceDubbo.dubboService().equals("")){
					clazz = Class.forName(url.getServiceInterface()+"Impl");
				}else{
					clazz = Class.forName(httpRestServiceDubbo.dubboService());
				}
			} catch (ClassNotFoundException e) {
				System.err.println(e.getMessage());
			}
			IRegister register = RegisterMap.get(address);
			if(register==null){
				return;
			}
			IAnnoToObject annoToObject = (IAnnoToObject) spiCoreContextHolder.getSpiObjectFromSpiObject("server.dubbox.AnnoToObject","cn.tomsnail.http.register.dubbo.DefaultAnnoToObject");
			if(annoToObject==null){
				return;
			}
			Path rootpath = (Path) clazz.getAnnotation(Path.class);
			Method[] ms = clazz.getMethods();
			for(Method method:ms){
				annoToObject.getObjectFromAnno(method, rootpath, url, httpRestServiceDubbo, register);
			}
		}
	}

	@Override
	public void subscribe(URL url, NotifyListener notifyListener) {
	}

	@Override
	public void unregister(URL url) {
		
	}

	@Override
	public void unsubscribe(URL url, NotifyListener notifyListener) {
		
	}


	
	

}

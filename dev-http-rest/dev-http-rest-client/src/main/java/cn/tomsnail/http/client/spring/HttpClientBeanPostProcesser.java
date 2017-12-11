package cn.tomsnail.http.client.spring;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import cn.tomsnail.zkclient.address.ZooKeeperAddress;
import cn.tomsnail.http.client.annotation.HttpClientServiceTraget;
import cn.tomsnail.http.client.register.IRegisterFactory;
import cn.tomsnail.http.client.service.ServiceFactory;
import cn.tomsnail.framwork.spi.DefaultSpiCoreContextHolder;
import cn.tomsnail.framwork.spi.SpiCoreContextHolder;

/**
 *        spring bean 加载时初始化客户端服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月5日 下午2:45:06
 * @see 
 */
@Component
public class HttpClientBeanPostProcesser implements BeanPostProcessor{
	
	private ServiceFactory serviceFactory = null;
	
	private IRegisterFactory registerFactory = null;
	
	SpiCoreContextHolder spiCoreContextHolder = new DefaultSpiCoreContextHolder();

	
	private String address = null;
	
	public HttpClientBeanPostProcesser(){
		try {
			address = ZooKeeperAddress.ZK_ADDRESS;
			serviceFactory = (ServiceFactory) spiCoreContextHolder.getSpiObjectFromSpiObject("client.service.factory", "cn.tomsnail.http.client.service.DefaultServiceFactory");
			registerFactory = (IRegisterFactory) spiCoreContextHolder.getSpiObjectFromSpiObject("client.register.factory", "cn.tomsnail.http.client.register.DefaultClientRegisterFactory");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
	
		Class clazz = bean.getClass();
		if(clazz.isAnnotationPresent(HttpClientServiceTraget.class)){
			Field[] fs = clazz.getDeclaredFields();
			IAnnoToObject annoToObject = (IAnnoToObject) spiCoreContextHolder.getSpiObjectFromSpiObject("client.register.factory", "cn.tomsnail.http.client.register.DefaultClientRegisterFactory");
			for(Field f:fs){
				annoToObject.buildInherintObject(bean, f, serviceFactory, registerFactory, address);
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}

package cn.tomsnail.http.client.spring;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;

import cn.tomsnail.config.client.plugin.AnnotationConverter;
import cn.tomsnail.host.AppName;
import cn.tomsnail.host.HostIP;
import cn.tomsnail.http.client.annotation.HttpClientService;
import cn.tomsnail.http.client.annotation.RegisterType;
import cn.tomsnail.http.client.register.ClientRegisterObject;
import cn.tomsnail.http.client.register.IRegister;
import cn.tomsnail.http.client.register.IRegisterFactory;
import cn.tomsnail.http.client.register.JsonClientRegisterObject;
import cn.tomsnail.http.client.service.CustomBasicService;
import cn.tomsnail.http.client.service.ServiceFactory;


  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年11月17日 下午3:46:52
	* @see 
	*/     
    
public class DefaultAnnoToObject implements IAnnoToObject{

	public void buildInherintObject(Object bean,Field f,ServiceFactory serviceFactory,IRegisterFactory registerFactory,String address) {
		if(f.getType().getCanonicalName().startsWith("cn.tomsnail.http.client.service.CustomBasicService")&&f.isAnnotationPresent(HttpClientService.class)){
			ClientRegisterObject clientRegisterObject = getRegisterObject(f);
			CustomBasicService customBasicService = getCustomBasicService(serviceFactory, clientRegisterObject);
			if(customBasicService==null){
				return;
			}
			if(clientRegisterObject.getRegisterType().equals(RegisterType.ZK.toString())){
				IRegister register = getRegister(registerFactory, clientRegisterObject);
				if(register!=null){
					try {
						register.register(clientRegisterObject);
						customBasicService.setClientRegisterObject(clientRegisterObject);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
//			if(httpClientService.mock()){
//				IInvoker invoker = new MockInvoker();
//				List<IInvoker> invokers = new ArrayList<IInvoker>();
//				invokers.add(invoker);
//				serviceFactory.setInvokers(invokers);
//			}
			f.setAccessible(true);
			try {
				f.set(bean, customBasicService);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected ClientRegisterObject getRegisterObject(Field f) {
		HttpClientService httpClientService = f.getAnnotation(HttpClientService.class);
		JsonClientRegisterObject clientRegisterObject = new JsonClientRegisterObject();
		clientRegisterObject.setService(httpClientService.serviceName());
		clientRegisterObject.setAddUrl(HostIP.HOST_IP);
		clientRegisterObject.setAppName(AppName.AppName);
		clientRegisterObject.setFailType(httpClientService.failType());
		clientRegisterObject.setLbType(httpClientService.lbType());
		clientRegisterObject.setTimeout(httpClientService.timeout());
		clientRegisterObject.setRetryCount(httpClientService.retryCount());
		clientRegisterObject.setAddress(httpClientService.address());
		clientRegisterObject.setRegisterType(httpClientService.registerType().toString());
		return clientRegisterObject;
	}

	protected CustomBasicService getCustomBasicService(ServiceFactory serviceFactory, ClientRegisterObject clientRegisterObject) {
		CustomBasicService customBasicService = null;
		String _address = AnnotationConverter.getValue(clientRegisterObject.getAddress());

		try {
			if(StringUtils.isBlank(_address)){
				customBasicService = (CustomBasicService) serviceFactory.getService(clientRegisterObject.getService(), clientRegisterObject.getAddress());
			}else{
				customBasicService = (CustomBasicService) serviceFactory.getService(clientRegisterObject.getService(), _address);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return customBasicService;
	}

	protected IRegister getRegister(IRegisterFactory registerFactory,ClientRegisterObject clientRegisterObject) {
		IRegister register = null;
		String _address = AnnotationConverter.getValue(clientRegisterObject.getAddress());
		if(StringUtils.isBlank(_address)){
			register = registerFactory.getRegister(clientRegisterObject.getAddress());
		}else{
			register = registerFactory.getRegister(_address);
		}
		return register;
	}
}

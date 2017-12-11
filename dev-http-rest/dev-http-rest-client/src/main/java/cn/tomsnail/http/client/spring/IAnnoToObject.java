package cn.tomsnail.http.client.spring;

import java.lang.reflect.Field;

import cn.tomsnail.http.client.register.IRegisterFactory;
import cn.tomsnail.http.client.service.ServiceFactory;


  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年11月17日 下午3:37:25
	* @see 
	*/     
    
public interface IAnnoToObject {

	public void buildInherintObject(Object bean,Field f,ServiceFactory serviceFactory,IRegisterFactory registerFactory,String address);
	
	
}

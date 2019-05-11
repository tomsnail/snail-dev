package cn.tomsnail.snail.core.http.register.springmvc;

import java.util.Map;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import cn.tomsnail.snail.core.http.register.core.IRegister;


  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年11月17日 下午3:37:25
	* @see 
	*/     
    
public interface IAnnoToObject {

	public void getObjectFromAnno(Map.Entry<RequestMappingInfo, HandlerMethod> m,IRegister register,Address address);
	
}

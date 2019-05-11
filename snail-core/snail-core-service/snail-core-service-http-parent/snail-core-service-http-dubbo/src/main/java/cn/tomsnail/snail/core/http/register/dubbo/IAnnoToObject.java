package cn.tomsnail.snail.core.http.register.dubbo;

import java.lang.reflect.Method;

import javax.ws.rs.Path;

import com.alibaba.dubbo.common.URL;

import cn.tomsnail.snail.core.http.register.annotation.HttpRestServiceDubbo;
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

	public void getObjectFromAnno(Method method,Path rootpath,URL url,HttpRestServiceDubbo httpRestServiceDubbo,IRegister register );
	
}

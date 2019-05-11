package cn.tomsnail.snail.core.http.register.springmvc;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import cn.tomsnail.snail.core.SpiCoreContextHolder;
import cn.tomsnail.snail.core.framework.spi.DefaultSpiCoreContextHolder;
import cn.tomsnail.snail.core.http.register.core.IRegister;
import cn.tomsnail.snail.core.http.register.core.IRegisterFactory;



/**
 *       springmvc下的注册，通过RequestMappingHandlerMapping获得url映射信息
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 上午11:52:12
 * @see 
 */
@Component
public class RestInitBean implements InitializingBean {
	
	@Autowired  
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@Resource
	private Address address;
		
	SpiCoreContextHolder spiCoreContextHolder = new DefaultSpiCoreContextHolder("dev-http-rest-server.properties");
		
	
	public RestInitBean(){
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		IRegisterFactory registerFactory =  (IRegisterFactory) spiCoreContextHolder.getSpiObjectFromSpiObject("server.RegisterFactory","cn.tomsnail.http.register.core.RegisterFactory");
		if(registerFactory==null){
			return;
		}
		IAnnoToObject annoToObject = (IAnnoToObject) spiCoreContextHolder.getSpiObjectFromSpiObject("server.springmvc.AnnoToObject","cn.tomsnail.http.register.springmvc.DefaultAnnoToObject");
		if(annoToObject==null){
			return;
		}
		if(address==null){
			return;
		}
		String add = null;
		if(StringUtils.isBlank(add)){
			add = address.getZookeeperAdd();
		}
		IRegister register = null;
		if(!StringUtils.isBlank(add)){
			register = registerFactory.getRegister(add);
		}
		if(register==null){
			return;
		}
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();  
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {  
        	annoToObject.getObjectFromAnno(m, register, address);
        }
	}


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}	
	
}

package cn.tomsnail.http.register.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;

/**
 *        扩展dubbo注册工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 上午10:30:17
 * @see 
 */
public class HttpRestfulRegisterFactory implements RegistryFactory {

	@Override
	public Registry getRegistry(URL url) {
		return new HttpRestfulRegister(url);
	}
	
	
	
}

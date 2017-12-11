package cn.tomsnail.http.client.register;

import java.util.HashMap;
import java.util.Map;

import cn.tomsnail.framwork.spi.DefaultSpiCoreContextHolder;
import cn.tomsnail.framwork.spi.SpiCoreContextHolder;

/**
 *        默认客户端注册工厂，返回zk注册客户端
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月29日 下午2:14:10
 * @see 
 */
public class DefaultClientRegisterFactory implements IRegisterFactory{
	
	/**
	 * 注册映射集合，用于注册于不同的地址
	 */
	private static final Map<String,IRegister> REGISTER_MAP = new HashMap<String, IRegister>();
	
	/**
	 * 
	 */
	SpiCoreContextHolder spiCoreContextHolder = new DefaultSpiCoreContextHolder();

	@Override
	public IRegister getRegister(String address) {
		try {
			if(REGISTER_MAP.containsKey(address)){
			}else{
				String className = spiCoreContextHolder.getSpiClass("client.register", "cn.tomsnail.http.client.register.BasicZkClientRegister");
				REGISTER_MAP.put(address, (IRegister) spiCoreContextHolder.getSpiObject(className, new Object[]{address}, new Class[] {String.class}));
			}
			return REGISTER_MAP.get(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

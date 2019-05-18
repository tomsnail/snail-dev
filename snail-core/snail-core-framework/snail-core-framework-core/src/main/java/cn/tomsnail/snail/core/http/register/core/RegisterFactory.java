package cn.tomsnail.snail.core.http.register.core;

import cn.tomsnail.snail.core.SpiCoreContextHolder;
import cn.tomsnail.snail.core.framework.spi.DefaultSpiCoreContextHolder;

/**
 *        默认注册器工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 上午11:55:53
 * @see 
 */
public class RegisterFactory implements IRegisterFactory {
	
	private SpiCoreContextHolder spiCoreContextHolder = null;

	public RegisterFactory(){
		spiCoreContextHolder = new DefaultSpiCoreContextHolder("config.properties");
	}

	@Override
	public IRegister getRegister(String url) {
		this.spiCoreContextHolder = spiCoreContextHolder;
		try {
			String className = (String) spiCoreContextHolder.getSpiClass("server.register", "cn.tomsnail.http.register.core.BasicZooKeeperRegister");
			return  (IRegister) spiCoreContextHolder.getSpiObject(className,new Object[]{url},  new Class[]{String.class});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SpiCoreContextHolder getSpiCoreContextHolder() {
		return spiCoreContextHolder;
	}

	public void setSpiCoreContextHolder(SpiCoreContextHolder spiCoreContextHolder) {
		this.spiCoreContextHolder = spiCoreContextHolder;
	}
}

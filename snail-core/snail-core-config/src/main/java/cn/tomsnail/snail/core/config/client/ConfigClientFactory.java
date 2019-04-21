package cn.tomsnail.snail.core.config.client;

/**
 *        配置客户端工厂，zookeeper客户端、文件客户端和默认客户端
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月21日 下午12:15:11
 * @see 
 */
public class ConfigClientFactory {
	
	private static final ConfigClientFactory instance = new ConfigClientFactory();
	
	private IConfigCilent configCilent;

	private ConfigClientFactory(){
//		configCilent = new ZookeeperConfigClient(new FileConfigClient(new DefaultConfigClient()));
		configCilent = new FileConfigClient(new ZookeeperConfigClient(new RedisConfigClient(new DbConfigClient(new DefaultConfigClient()))));
	}
	
	public static ConfigClientFactory getInstance(){
		return instance;
	}
	
	public IConfigCilent getConfigClient(){
		return configCilent;
	}
	
	public IConfigCilent getConfigClient(Class<? extends IConfigChangeListener> clazz){
		configCilent.addListener(clazz);
		return configCilent;
	}
	
	public static String getConfig(String key,String defaultValue){
		return instance.getConfigClient().getConfig(key, defaultValue);
	} 
	
}

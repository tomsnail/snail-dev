package cn.tomsnail.snail.core.config.client;

/**
 *        继承方式举例
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月21日 下午12:15:15
 * @see 
 */
public class ConfigExample extends DefaultConfigChangeListener{
	
	public static String TEST_KEY = ConfigClientFactory.getInstance().getConfigClient(ConfigExample.class).getConfig("TEST_KEY","value2");
	
	

	
}

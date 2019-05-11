package cn.tomsnail.snail.core.util.zkclient.address;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月26日 下午1:30:47
 * @see 
 */
public class ConfigFileAddress implements IAddress{

	@Override
	public String getAddress() {
		try {
			return ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.address", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

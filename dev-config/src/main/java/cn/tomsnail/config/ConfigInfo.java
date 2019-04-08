package cn.tomsnail.config;

import cn.tomsnail.util.configfile.ConfigHelp;

/**
 *        配置信息
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午3:47:11
 * @see 
 */
public class ConfigInfo {

	/**
	 * ZOOKEEPER根目录
	 */
	public static  final String rootNode = ConfigHelp.getInstance("config").getLocalConfig("ConfigInfo.rootNode", "/ts/config/root");

	
}

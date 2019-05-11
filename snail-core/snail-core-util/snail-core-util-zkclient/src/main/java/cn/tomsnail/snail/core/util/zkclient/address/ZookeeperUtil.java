package cn.tomsnail.snail.core.util.zkclient.address;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

public class ZookeeperUtil {
	
	
	private static int connectionTimeout = 30000;

	public static void set(String key,String value,String zookeeperServer){
		
	}
	
	public static String get(String key,String zookeeperServer){
		return null;
	}
	
	
	private static ZkClient getZkClient(String zookeeperServer){
		
		if(StringUtils.isBlank(zookeeperServer)){
			throw new NullPointerException("zookeeperServer is null");
		}
		ZkClient zkClient = zkClient = new ZkClient(zookeeperServer,connectionTimeout);
		return zkClient;
	}
	
	private static void colse(ZkClient zkClient){
		if(zkClient!=null){
			zkClient.close();
		}
	}
	
}

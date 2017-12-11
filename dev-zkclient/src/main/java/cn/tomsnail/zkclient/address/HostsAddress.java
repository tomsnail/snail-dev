package cn.tomsnail.zkclient.address;

import java.net.InetAddress;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月26日 下午1:29:35
 * @see 
 */
public class HostsAddress implements IAddress{

	private static final String HOSTNAME =  "ZooKeeperHost";
	
	@Override
	public String getAddress() {
		InetAddress ia = null;
		try {
			ia = InetAddress.getByName(HOSTNAME);
		} catch (Exception e) {
		}
		if(ia==null){
			return null;
		}else{
			return ia.getHostAddress()+":2181";
		}
	}

}

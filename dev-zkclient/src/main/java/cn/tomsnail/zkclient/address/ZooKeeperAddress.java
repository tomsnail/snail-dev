package cn.tomsnail.zkclient.address;

import java.util.ArrayList;
import java.util.List;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月26日 下午1:26:23
 * @see 
 */
public class ZooKeeperAddress implements IAddress{
	
	private List<IAddress> addresses = new ArrayList<IAddress>();
	
	public static String ZK_ADDRESS = new ZooKeeperAddress().getAddress();
	
	public ZooKeeperAddress(){
		addresses.add(new EnvAddress());
		addresses.add(new HostsAddress());
		addresses.add(new ConfigFileAddress());
	}
	
	@Override
	public String getAddress() {
		for(IAddress address:addresses){
			if(address.getAddress()!=null){
				return address.getAddress();
			}
		}
		return "127.0.0.1:2181";
	}
	
	public static void main(String[] args) {
		System.out.println(ZooKeeperAddress.ZK_ADDRESS);
		System.out.println(ZooKeeperAddress.ZK_ADDRESS);
	}
	
}

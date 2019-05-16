package cn.tomsnail.snail.core.util.host;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 *        主机IP地址
 *        读取规则：优先读取hosts文件中的配置，主机名和对应IP，如果没有设置，读取到的127.0.0.1,这时会去读取所有网卡的信息，随机选择一个作为IP
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月26日 上午10:56:47
 * @see 
 */
public class HostIP {

	public final static String HOST_IP = getHostNameIP();
	
	public static String getHostNameIP(){
		InetAddress ia = null;
		String ip = null;
		try {
			ia = InetAddress.getByName(InetAddress.getLocalHost().getHostName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		 if(ia==null||ia.isLoopbackAddress()||ia.isLinkLocalAddress()||ia.isMulticastAddress()){
			 ip = getHostIP();
		 }else{
			 ip = ia.getHostAddress();
		 }
		 return ip;
	}
	
	public static String getHostIP(){
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;// 是否找到外网IP
			while (netInterfaces.hasMoreElements() && !finded) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> address = ni.getInetAddresses();
				while (address.hasMoreElements()) {
					ip = address.nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
						netip = ip.getHostAddress();
						finded = true;
						break;
					} else if (ip.isSiteLocalAddress()
							&& !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
						localip = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
	
	public static String getHostIP(String defaultPort){
		return getHostIP()+":"+defaultPort;
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(HOST_IP);
	}
	
}

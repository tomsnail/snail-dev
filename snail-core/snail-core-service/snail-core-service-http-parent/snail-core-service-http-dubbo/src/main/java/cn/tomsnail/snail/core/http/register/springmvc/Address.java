package cn.tomsnail.snail.core.http.register.springmvc;

/**
 *        spring 服务地址
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 下午5:36:53
 * @see 
 */
public class Address {

	private String ip = "127.0.0.1";
	
	private int port = 8080;
	
	private String context = "/";
	
	private String zookeeperAdd = "";

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getZookeeperAdd() {
		return zookeeperAdd;
	}

	public void setZookeeperAdd(String zookeeperAdd) {
		this.zookeeperAdd = zookeeperAdd;
	}
	
	
	
}

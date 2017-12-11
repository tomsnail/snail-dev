package cn.tomsnail.framwork.zk;

import org.I0Itec.zkclient.ZkClient;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年5月24日 下午1:53:49
 * @see 
 */
public class ZookeeperClient {

	private String address;
	
	private ZkClient zkClient;
	
	private int sessionTimeout;
	private int connectionTimeout = 30000;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ZkClient getZkClient() {
		return zkClient;
	}

	public void setZkClient(ZkClient zkClient) {
		this.zkClient = zkClient;
	}
	
	public boolean connect(String address){
		if(address==null||address.equals("")){
			return false;
		}
		if(zkClient==null){
			try {
				zkClient = new ZkClient(address,connectionTimeout);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return false;
	}
	
	public boolean connect(String address,int connectionTimeout){
		if(address==null||address.equals("")){
			return false;
		}
		if(zkClient==null){
			try {
				zkClient = new ZkClient(address,connectionTimeout);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return false;
	}
	
	public boolean connect(){
		return connect(this.address);
	}
	
	public void close(){
		if(zkClient!=null){
			zkClient.close();
		}
		zkClient = null;
	}
	
	public boolean check(){
		
		return false;
	}

	public int getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	
	
}

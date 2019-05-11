package cn.tomsnail.snail.core.http.register.core;

import org.I0Itec.zkclient.ZkClient;

import cn.tomsnail.snai.core.service.http.core.RegisterObject;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.core.util.zkclient.address.ZooKeeperAddress;

/**
 *        基础zk注册类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 上午11:54:56
 * @see 
 */
public class BasicZooKeeperRegister implements IRegister{
	
	/**
	 * zk根
	 */
	private static final String ROOT = "/http/rest";
	
	/**
	 * zk地址
	 */
	private String address;
	
	private ZkClient zkClient;
	
	public BasicZooKeeperRegister(String address) throws Exception{
		this.address = ZooKeeperAddress.ZK_ADDRESS;
		init();
	}

	@Override
	public void register(RegisterObject registerObject) {
		JsonServerRegisterObject _registerObject = (JsonServerRegisterObject) registerObject;
		createProviderNode(_registerObject);		
		createGroupNode(_registerObject);
		createCustomerNode(_registerObject);
		writeGroupData(_registerObject);
		createChangeNode(_registerObject);
		createServiceNodeAndData(_registerObject);
	
	}

	private void createServiceNodeAndData(JsonServerRegisterObject _registerObject) {
		zkClient.createEphemeralSequential(ROOT+"/"+_registerObject.getService()+"/providers/"+_registerObject.getService(), _registerObject.geRegisterInfo());
	}

	private void createChangeNode(JsonServerRegisterObject _registerObject) {
		if(zkClient.exists(ROOT+"/t/"+_registerObject.getService())){
		}else{
			zkClient.createPersistent(ROOT+"/t/"+_registerObject.getService(), true);
		}
	}

	private void writeGroupData(JsonServerRegisterObject _registerObject) {
		zkClient.writeData(ROOT+"/"+_registerObject.getService()+"/group",_registerObject.geGroupRegisterInfo());
	}

	private void createCustomerNode(JsonServerRegisterObject _registerObject) {
		if(zkClient.exists(ROOT+"/"+_registerObject.getService()+"/customers")){
		}else{
			zkClient.createPersistent(ROOT+"/"+_registerObject.getService()+"/customers", true);
		}
	}

	private void createGroupNode(JsonServerRegisterObject _registerObject) {
		if(zkClient.exists(ROOT+"/"+_registerObject.getService()+"/group")){
		}else{
			zkClient.createPersistent(ROOT+"/"+_registerObject.getService()+"/group", true);
		}
	}

	private void createProviderNode(JsonServerRegisterObject _registerObject) {
		if(zkClient.exists(ROOT+_registerObject.getService())){
		}else{
			zkClient.createPersistent(ROOT+"/"+_registerObject.getService()+"/providers", true);
		}
		if(zkClient.exists(ROOT+"/"+_registerObject.getService()+"/providers")){
		}else{
			zkClient.createPersistent(ROOT+"/"+_registerObject.getService()+"/providers", true);
		}
	}
	
	private void init() throws Exception{
		if(StringUtils.isBlank(address)){
			throw new Exception("");
		}
		zkClient = new ZkClient(address);
		if(!zkClient.exists(ROOT)){
			zkClient.createPersistent(ROOT, true);
		}
	}

}

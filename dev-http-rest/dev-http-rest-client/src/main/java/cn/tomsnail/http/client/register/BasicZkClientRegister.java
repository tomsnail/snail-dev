package cn.tomsnail.http.client.register;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang.StringUtils;


/**
 *        httprest客户端zk注册
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月29日 下午2:02:43
 * @see 
 */
public class BasicZkClientRegister implements IRegister{
	
	/**
	 * zk根路径
	 */
	private static final String ROOT = "/http/rest";
	
	/**
	 * zk地址
	 */
	private String address;
	
	/**
	 * zk客户端
	 */
	private ZkClient zkClient;
	
	public BasicZkClientRegister(String address) throws Exception{
		this.address = address;
		init();
	}

	@Override
	public void register(ClientRegisterObject registerObject) {
		if(zkClient.exists(ROOT+registerObject.getService())){
		}else{
			zkClient.createPersistent(ROOT+"/"+registerObject.getService()+"/providers", true);
		}		
		if(zkClient.exists(ROOT+"/"+registerObject.getService()+"/providers")){
		}else{
			zkClient.createPersistent(ROOT+"/"+registerObject.getService()+"/providers", true);
		}
		if(zkClient.exists(ROOT+"/"+registerObject.getService()+"/group")){
		}else{
			zkClient.createPersistent(ROOT+"/"+registerObject.getService()+"/group", true);
		}
		if(zkClient.exists(ROOT+"/"+registerObject.getService()+"/customers")){
		}else{
			zkClient.createPersistent(ROOT+"/"+registerObject.getService()+"/customers", true);
		}
		if(zkClient.exists(ROOT+"/t/"+registerObject.getService())){
		}else{
			zkClient.createPersistent(ROOT+"/t/"+registerObject.getService(), true);
		}
		zkClient.createEphemeralSequential(ROOT+"/"+registerObject.getService()+"/customers/"+registerObject.getService(),registerObject.geRegisterInfo());
	}
	
	/**
	 *        初始化
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年8月29日 下午2:14:40
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
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

package cn.tomsnail.pilot.client;



import cn.tomsnail.framwork.zk.ZkHelper;

import cn.tomsnail.pilot.model.Consts;
import cn.tomsnail.pilot.model.ServiceProcess;
import cn.tomsnail.pilot.model.ServiceStatus;
import cn.tomsnail.pilot.util.AddressUtil;
import cn.tomsnail.pilot.util.ConfigHelp;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 下午3:27:03
 * @see 
 */
public class ZkClient implements IClient{
	
	private ZkHelper zkHelper = null;
	
	private String serviceIP;
	
	private String serviceName;
	
	private String alias;
	
	private String servicePath;
	
	private String status;
		
	
	public ZkClient(){
		
	}
	
	public ZkClient(String serviceName,String serviceIP){
		this.serviceName = serviceName;
		this.serviceIP = serviceIP;
	}
	
	public ZkClient(String serviceName){
		this.serviceName = serviceName;
	}
	

	
	public void init() {
		ConfigHelp.initPath(this.getClass());
		zkHelper = ZkHelper.getInstance(ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.address", ""));
		if(this.serviceName==null){
			serviceName = ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.serviceName", "Sevice_"+System.currentTimeMillis());
		}
		if(this.alias==null){
			alias = ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.alias", "Sevice_"+System.currentTimeMillis());
		}
		if(this.serviceName==null){
			serviceName = ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.serviceName", "Sevice_"+System.currentTimeMillis());
		}
		status = ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.client.status", ServiceStatus.ACTIVE);
		if(this.serviceIP==null){
			try {
				serviceIP = ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.serviceIP",AddressUtil.getLocalAddr());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	public void register(Object info) {
		if(zkHelper==null) return;
		if(!zkHelper.isExtisPath(Consts.ZK_ROOT)){
			zkHelper.createPathWithParent(Consts.ZK_ROOT, true);
		}
		String nodePath = Consts.ZK_ROOT+"/"+serviceIP;
		if(!zkHelper.isExtisPath(nodePath)){
			zkHelper.createPath(nodePath);
		}
		//if(!canStart(serviceName,(ServiceProcess) info)) return;
		servicePath = nodePath+"/"+serviceName.split("_")[0];
		if(!zkHelper.isExtisPath(servicePath)){
			zkHelper.createNode(servicePath, info, true);
		}else{
			System.err.println("same service name path is extis,system exit");
			System.exit(0);
		}
	}

	
	
	public void start() {
		
	}

	
	public void stop() {
		
	}

	
	public void destory() {
		
	}

	
	public void send(Object info) {
		if(zkHelper==null) return;
		if(!zkHelper.isExtisPath(servicePath)) return;
		zkHelper.writeNode(servicePath, info);
	}

	
	public ServiceProcess getClientInfo() {
		ServiceProcess serviceProcess = new ServiceProcess();
		serviceProcess.setName(serviceName);
		serviceProcess.setAlias(this.alias);
		serviceProcess.setIp(serviceIP);
		serviceProcess.setNode(serviceIP);
		serviceProcess.setStatus(status);
		return serviceProcess;
	}
}

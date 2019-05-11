package cn.tomsnail.snail.ext.pilot.core.client;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.configfile.ProgramPathHelper;
import cn.tomsnail.snail.core.util.zkclient.ZkHelper;
import cn.tomsnail.snail.ext.pilot.core.client.listener.NodeChangeListener;
import cn.tomsnail.snail.ext.pilot.core.client.listener.ProxyChangeListener;
import cn.tomsnail.snail.ext.pilot.core.client.policy.IVotePolicy;
import cn.tomsnail.snail.ext.pilot.core.client.policy.SimpleVotePolicy;
import cn.tomsnail.snail.ext.pilot.core.model.Consts;
import cn.tomsnail.snail.ext.pilot.core.model.ProxyInfo;
import cn.tomsnail.snail.ext.pilot.core.model.ServiceProcess;
import cn.tomsnail.snail.ext.pilot.core.model.SmartProxyInfo;
import cn.tomsnail.snail.ext.pilot.core.util.AddressUtil;

//import org.apache.log4j.Logger;







/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 下午6:01:18
 * @see 
 */
public class SmartZkSubProxy extends ASubProxy{
	
	//private static final Logger LOGGER = Logger.getLogger(SmartZkSubProxy.class);

	private ZkHelper zkHelper = null;
	
	private String serviceIP;
		
	private IVotePolicy policy;
	
	
	public SmartZkSubProxy(){
		
	}
	
	public SmartZkSubProxy(String serviceIP){
		this.serviceIP = serviceIP;
	}
				
	
	public int init() {
		zkHelper = ZkHelper.getInstance(ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.address", ""));
		policy = new SimpleVotePolicy(zkHelper);
		initZk();
		listener();
		return clientType;
	}
	
	
	private void initZk() {
		if(serviceIP==null){
			try {
				serviceIP = ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.serviceIP", AddressUtil.getLocalAddr());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.ignorList = ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.ignorList", "");
		if(!zkHelper.isExtisPath(Consts.ZK_PROXY)){
			zkHelper.createPath(Consts.ZK_PROXY);
		}
		if(zkHelper.isExtisPath(getProxyPath())||!zkHelper.createNode(getProxyPath(),  getProxyInfo(), true)){
			clientType = 0;
		}else{
			clientType = clientType+1;
		}
	}

	private ProxyInfo getProxyInfo() {
		ServiceProcess serviceProcess = new ServiceProcess();
		serviceProcess.setRootDir(ProgramPathHelper.getProgramPath(this.getClass()));
		serviceProcess.setName(this.name);
		serviceProcess.setAlias(this.alias);
		serviceProcess.setIp(serviceIP);
		ProxyInfo proxyInfo = new ProxyInfo();
		proxyInfo.setSelfInfo(serviceProcess);
		return proxyInfo;
	}
	
	private String getProxyPath(){
		return Consts.ZK_PROXY+"/"+serviceIP;
	}

	
	public void start() {
		
	}

	
	public void stop() {
		
	}

	
	public void listener() {
		//如果发现变化，自己抢占proxy，如果抢占成功则判断服务节点信息，如果不存在则重启
		//并接受服务端指令，启动、停止、重启指定模块
		//如果抢占不成功，监听proxy
		//如果指定为客户端模式，则不进行监听
		if(clientType==0){
			listenerSelfProxyChange();
		}
		if(clientType==1){
			listenerNodeChange();
			listenerAllProxyChange();
			listenerNodeDataChange();
			resetNodeData();
		}
	}
	
	private void resetNodeData(){
		SmartProxyInfo proxyInfo = (SmartProxyInfo) zkHelper.readNode(Consts.ZK_ROOT+"/"+serviceIP);
		sendCommand(proxyInfo);
	}
	
	private void listenerNodeChange(){
		zkHelper.getZkClient().subscribeChildChanges(Consts.ZK_ROOT+"/"+serviceIP,new NodeChangeListener(serviceIP, zkHelper, policy));
	}

	private void listenerAllProxyChange(){
		if(zkHelper.isExtisPath(Consts.ZK_SERVICE_KEEPER)){
			zkHelper.getZkClient().subscribeChildChanges(Consts.ZK_PROXY, new ProxyChangeListener(zkHelper, policy));
		}
	}
	
	
	private void listenerSelfProxyChange(){
		zkHelper.getZkClient().subscribeChildChanges(getProxyPath(), new IZkChildListener() {
			
			public void handleChildChange(String parentPath, List<String> currentChilds)
					throws Exception {
				if(isProxyDeleted(currentChilds)){
					zkHelper.getZkClient().unsubscribeChildChanges(getProxyPath(), this);
					zkHelper.deletePath(getProxyPath());
					initZk();
					listener();
				}
			}
		});
	}
	
	private boolean isProxyDeleted(List<String> currentChilds){
		return currentChilds==null&&!zkHelper.isExtisPath(getProxyPath());
	}
	
	private void listenerNodeDataChange(){
		zkHelper.getZkClient().subscribeDataChanges(Consts.ZK_ROOT+"/"+serviceIP, new IZkDataListener() {
			
			public void handleDataDeleted(String dataPath) throws Exception {
			}
			
			public void handleDataChange(String dataPath, Object data) throws Exception {
				SmartProxyInfo smartProxyInfo = (SmartProxyInfo) data;
				sendCommand(smartProxyInfo);
			}
		});
	}

	private void sendCommand(SmartProxyInfo proxyInfo) {
		if(proxyInfo==null){
			return;
		}
		List<ServiceProcess> processes = proxyInfo.getCommandInfo()==null?proxyInfo.getConfigList():proxyInfo.getCommandInfo();
		if(processes==null){
			return;
		}
		for(ServiceProcess serviceProcess:processes){
			if(!policy.canStart(serviceProcess)) continue;
			manageService(serviceProcess,true);
		}
	}
}

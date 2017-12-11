package cn.tomsnail.pilot.client;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;

import cn.tomsnail.framwork.zk.ZkHelper;
import cn.tomsnail.pilot.model.Consts;
import cn.tomsnail.pilot.model.ProxyInfo;
import cn.tomsnail.pilot.model.ServiceProcess;
import cn.tomsnail.pilot.util.AddressUtil;
import cn.tomsnail.pilot.util.ConfigHelp;
import cn.tomsnail.util.configfile.ProgramPathHelper;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 下午6:01:18
 * @see 
 */
public class ZkSubProxy extends ASubProxy{

	private ZkHelper zkHelper = null;
	
	private String serviceIP;
	
	private ProxyInfo proxyInfo;
	
	private IZkChildListener rootChildListener;
			
	@Override
	public int init() {
		initListener();
		initZk();
		listener();
		return clientType;
	}
	
	private void initListener(){
		rootChildListener = new IZkChildListener(){
			@Override
			public void handleChildChange(String parentPath,List<String> currentChilds) throws Exception {
				sendCommand(proxyInfo);
			}
			
		};
	}

	private void initZk() {
		zkHelper = ZkHelper.getInstance(ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.address", ""));
		try {
			serviceIP = ConfigHelp.getInstance("zookeeper").getLocalConfig("zk.serviceIP", AddressUtil.getLocalAddr());
		} catch (Exception e) {
			e.printStackTrace();
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
		serviceProcess.setIp(serviceIP);
		ProxyInfo proxyInfo = new ProxyInfo();
		proxyInfo.setSelfInfo(serviceProcess);
		return proxyInfo;
	}
	
	private String getProxyPath(){
		return Consts.ZK_PROXY+"/"+serviceIP;
	}

	@Override
	public void start() {
		
	}

	@Override
	public void stop() {
		
	}

	@Override
	public void listener() {
		//如果发现变化，自己抢占proxy，如果抢占成功则判断服务节点信息，如果不存在则重启
		//并接受服务端指令，启动、停止、重启指定模块
		//如果抢占不成功，监听proxy
		//如果指定为客户端模式，则不进行监听
		if(clientType==0){
			listenerProxyPath();
		}
		if(clientType==1){
			listenerProxyNode();
			listenerProxyServer();
		}
	
	}
	
	private void listenerProxyPath(){
		zkHelper.getZkClient().subscribeChildChanges(getProxyPath(), new IZkChildListener() {
			@Override
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
	
	private void listenerProxyNode(){
		zkHelper.getZkClient().subscribeDataChanges(getProxyPath(), new IZkDataListener() {
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
			}
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				ProxyInfo proxyInfo = (ProxyInfo) data;
				sendCommand(proxyInfo);
			}
		});
	}
	
	private void listenerProxyServer(){
		if(!zkHelper.isExtisPath(Consts.ZK_PROXY_SERVER)){
			resetNodeService();
		}
		zkHelper.getZkClient().subscribeChildChanges(Consts.ZK_PROXY_SERVER, new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds)
					throws Exception {
				if(currentChilds==null){//服务端停止
					resetNodeService();
				}else{
					zkHelper.getZkClient().unsubscribeChildChanges(Consts.ZK_ROOT+"/"+serviceIP, rootChildListener);
				}
			}
		});	
	}
	private void resetNodeService() {
		proxyInfo = (ProxyInfo) zkHelper.readNode(Consts.ZK_ROOT+"/"+serviceIP);
		sendCommand(proxyInfo);
		zkHelper.getZkClient().subscribeChildChanges(Consts.ZK_ROOT+"/"+serviceIP,rootChildListener);
	}

	private void sendCommand(ProxyInfo proxyInfo) {
		if(proxyInfo==null||proxyInfo.getCommandInfo()==null){
			return;
		}
		for(ServiceProcess serviceProcess:proxyInfo.getCommandInfo()){
			manageService(serviceProcess,true);
		}
	}
}

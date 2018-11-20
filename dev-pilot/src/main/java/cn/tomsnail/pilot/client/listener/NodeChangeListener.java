package cn.tomsnail.pilot.client.listener;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.framwork.zk.ZkHelper;
import cn.tomsnail.pilot.client.policy.IVotePolicy;
import cn.tomsnail.pilot.model.Consts;
import cn.tomsnail.pilot.model.ServiceProcess;
import cn.tomsnail.pilot.model.SmartProxyInfo;



/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月11日 上午9:25:28
 * @see 
 */
public class NodeChangeListener implements IZkChildListener{
	
	private String serviceIP;
	
	private ZkHelper zkHelper;
	
	private IVotePolicy policy;
	
	
	
	public NodeChangeListener(String serviceIP, ZkHelper zkHelper, IVotePolicy policy) {
		this.serviceIP = serviceIP;
		this.zkHelper = zkHelper;
		this.policy = policy;
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(NodeChangeListener.class);

	
	public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
		LOGGER.info(Consts.ZK_ROOT+"/"+serviceIP+" Change : start");
		SmartProxyInfo proxyInfo = (SmartProxyInfo) zkHelper.readNode(Consts.ZK_ROOT+"/"+serviceIP);
		if(currentChilds!=null&&currentChilds.size()>0&&proxyInfo!=null){
			List<ServiceProcess> processes = proxyInfo.getConfigList()==null?proxyInfo.getCommandInfo():proxyInfo.getConfigList();
			if(processes==null) return;
			for(ServiceProcess process:processes){
				if(!isSame(currentChilds, process)){
					String pPath = policy.getNodePath(process);
					if(pPath==null){
						pPath = Consts.ZK_ROOT+"/"+process.getNode();
					}
					SmartProxyInfo _proxyInfo = (SmartProxyInfo) zkHelper.readNode(pPath);
					zkHelper.writeNode(pPath, _proxyInfo);
				}
			}
		}
		LOGGER.info(Consts.ZK_ROOT+"/"+serviceIP+" Change : end");
	}
	private boolean isSame(List<String> currentChilds,
			ServiceProcess process) {
		boolean isSame = false;
		for(String c:currentChilds){
			if(process.getName().split("_")[0].equals(c)){
				isSame = true;
				break;
			}
		}
		return isSame;
	}	
}

package cn.tomsnail.snail.ext.pilot.core.client.listener;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.snail.core.util.zkclient.ZkHelper;
import cn.tomsnail.snail.ext.pilot.core.client.policy.IVotePolicy;
import cn.tomsnail.snail.ext.pilot.core.model.Consts;
import cn.tomsnail.snail.ext.pilot.core.model.ServiceProcess;
import cn.tomsnail.snail.ext.pilot.core.model.SmartProxyInfo;




/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月11日 上午9:25:24
 * @see 
 */
public class ProxyChangeListener implements IZkChildListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProxyChangeListener.class);


	private ZkHelper zkHelper;

	private IVotePolicy policy;

	public ProxyChangeListener(ZkHelper zkHelper, IVotePolicy policy) {
		super();
		this.zkHelper = zkHelper;
		this.policy = policy;
	}

	
	public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
		LOGGER.info(Consts.ZK_PROXY + " Change : start");
		// 谁先接收到谁处理
		boolean unlock = zkHelper.createNode("/pilot/lock", "", true);
		try {
			if (!unlock) {
				LOGGER.info(Consts.ZK_PROXY + " Change : unlock " + unlock);
				return;
			}
			List<String> tNodes = getChangedNodes();
			LOGGER.info(Consts.ZK_PROXY + " Change : tNodes " + tNodes.size());
			for (String c : tNodes) {
				LOGGER.info(Consts.ZK_PROXY + " Change : c " + c);
				SmartProxyInfo proxyInfo = (SmartProxyInfo) zkHelper.readNode(Consts.ZK_ROOT + "/" + c);
				List<ServiceProcess> _list = proxyInfo.getCommandInfo() == null ? proxyInfo.getConfigList()
						: proxyInfo.getCommandInfo();
				if (_list == null) {
					LOGGER.info(Consts.ZK_PROXY + " Change : _list " + _list);
					continue;
				}
				changeVotedNode(_list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		zkHelper.deletePath("/pilot/lock");
		LOGGER.info(Consts.ZK_PROXY + " Change : end");
	}

	private List<String> getChangedNodes() {
		List<String> cNodes = zkHelper.getPath(Consts.ZK_PROXY);
		List<String> tNodes = zkHelper.getPath(Consts.ZK_ROOT);
		for (String c : cNodes) {
			tNodes.remove(c);
		}
		return tNodes;
	}

	private void changeVotedNode(List<ServiceProcess> _list) {
		for (ServiceProcess process : _list) {
			String pPath = policy.getNodePath(process);
			if (pPath == null)
				continue;
			SmartProxyInfo _proxyInfo = (SmartProxyInfo) zkHelper.readNode(pPath);
			zkHelper.writeNode(pPath, _proxyInfo);
		}
	}
}

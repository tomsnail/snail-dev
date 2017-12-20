package cn.tomsnail.config.client;


import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;

import cn.tomsnail.config.ConfigInfo;
import cn.tomsnail.zkclient.address.ZooKeeperAddress;
import cn.tomsnail.framwork.zk.ZkHelper;


/**
 *        zookeeper配置客户端
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月21日 下午12:15:06
 * @see 
 */
public class ZookeeperConfigClient extends AConfigCilent{
	
	private static  ZkHelper ZK_HELPER = null;

	public ZookeeperConfigClient(){
		try {
			init();
			this.inited = true;
		} catch (Exception e) {
		}
	}
	
	public ZookeeperConfigClient(AConfigCilent configCilent){
		this.configCilent = configCilent;
		try {
			if(isDo()){
				init();
				this.inited = true;
			}
		} catch (Exception e) {
		}
	}
	
	
	@Override
	public String getConfig(String key) {
		if(ZK_HELPER==null){
			return null;
		}
		String value = null;
		try {
			String path = ConfigInfo.rootNode+"/"+key.replaceAll("_", "/").replace(".", "/");
			value = ZK_HELPER.readNode(path)+"";
			subscribeData(path);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		if(isEmpty(value)){
			value =  null;
		}
		return value;
	}
		
	private void init(){
		String address = ZooKeeperAddress.ZK_ADDRESS;
		ZK_HELPER = ZkHelper.getInstance(address);
		if(!ZK_HELPER.isExtisPath(ConfigInfo.rootNode)){
			ZK_HELPER.createPathWithParent(ConfigInfo.rootNode, true);
		}
		subscribeChild();
	}
	
	private void subscribeChild(){
		ZK_HELPER.getZkClient().subscribeChildChanges(ConfigInfo.rootNode, new IZkChildListener() {
			@Override
			public void handleChildChange(String arg0, List<String> arg1) throws Exception {
				changeHandler();
				subscribeChild();
			}
		});
	}
	
	private void changeHandler(){
		configMap.clear();
		changed();
	}
	
	private void subscribeData(final String path){
		ZK_HELPER.getZkClient().subscribeDataChanges(path, new IZkDataListener() {
			
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				changeHandler();
			}
			
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				changeHandler();
			}
		});
	}

	

	@Override
	protected String getName() {
		return "zookeeper";
	}
}

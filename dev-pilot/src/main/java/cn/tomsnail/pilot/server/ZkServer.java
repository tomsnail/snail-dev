package cn.tomsnail.pilot.server;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;

import cn.tomsnail.util.configfile.ConfigHelp;
import cn.tomsnail.zkclient.address.ZooKeeperAddress;
import cn.tomsnail.framwork.zk.ZkHelper;
import cn.tomsnail.pilot.server.listener.IListenerHandler;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月6日 上午11:23:17
 * @see 
 */
public abstract class ZkServer extends AServer{

	private static final ZkHelper zkHelper =  ZkHelper.getInstance(ZooKeeperAddress.ZK_ADDRESS);
	
	protected void listenerHandler(String path,final IListenerHandler handler){
		zkHelper.getZkClient().subscribeChildChanges(path, new IZkChildListener() {
			
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				if(handler!=null) handler.handler(parentPath, currentChilds);
			}
		});
	}
	
	protected List<String> getPath(String path){
		return zkHelper.getPath(path);
	}
	
	protected Object readObject(String path){
		if(!zkHelper.isExtisPath(path)){
			return null;
		}
		return zkHelper.readNode(path);
	}
	
	protected boolean delete(String path){
		if(!zkHelper.isExtisPath(path)){
			return true;
		}
		return zkHelper.deletePath(path);
	}
	
	protected boolean create(String path,boolean isTemp){
		if(zkHelper.isExtisPath(path)){
			return true;
		}
		return zkHelper.createNode(path, "", true);
	}
	
	protected void writeObject(String path,Object data){
		if(!zkHelper.isExtisPath(path)){
			zkHelper.createPathWithParent(path, true);
		}
		zkHelper.writeNode(path, data);
	}
	
	protected IZkChildListener registerChildListener(String path,final IListenerHandler handler){
		IZkChildListener childListener = new IZkChildListener() {
			
			public void handleChildChange(String parentPath, List<String> currentChilds)
					throws Exception {
				handler.handler(parentPath, currentChilds);
			}
		};
		zkHelper.getZkClient().subscribeChildChanges(path, childListener);
		return childListener;
	}
	
	public void unsubscribeChildListener(String path,IZkChildListener zkChildListener){
		zkHelper.getZkClient().unsubscribeChildChanges(path, zkChildListener);
	}

}

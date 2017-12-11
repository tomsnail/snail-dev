package cn.tomsnail.http.client.cluster;

import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang.StringUtils;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.register.core.JsonServerRegisterObject;



/**
 *        zookeeper集群服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午5:10:41
 * @see 
 */
public abstract class ZkUriBasicClusterService extends AbstractBasicClusterService{
	
	
	/**
	 * 根路径
	 */
	private static final String ROOT = "/http/rest";
	
	private ZkClient zkClient;

	/**
	 * zookeeper地址
	 */
	private String address;
	
	public ZkUriBasicClusterService(String address) throws Exception{
		this.address = address;
		init();
	}
	
	public ZkUriBasicClusterService(){
		
	}

	private void init() throws Exception{
		initZK();
	}
	

	protected void initZK() throws Exception {
		if(StringUtils.isBlank(address)){
			throw new Exception("");
		}
		zkClient = new ZkClient(address);
		if(!zkClient.exists(ROOT)){
			zkClient.createPersistent(ROOT, true);
		}
	}

	@Override
	public void loadUriMap(final String serviceName) {
		if(zkClient.exists(ROOT+"/"+serviceName+"/providers")){
			load(serviceName);
			zkClient.subscribeChildChanges(ROOT+"/"+serviceName+"/providers", new IZkChildListener() {
				@Override
				public void handleChildChange(String parentPath, List<String> currentChilds)
						throws Exception {
					load(serviceName);
				}
			});
		}
	}
	
	private void load(String serviceName){
		List<String> list = zkClient.getChildren(ROOT+"/"+serviceName+"/providers");
		if(list!=null&&list.size()>0){
			List<ClusterRequestObject> requests = new ArrayList<ClusterRequestObject>();
			for(String path:list){
				String p = ROOT+"/"+serviceName+"/providers/"+path;
				String data = zkClient.readData(p);
				JsonServerRegisterObject jsonServerRegisterObject = JsonServerRegisterObject.newRegisterObject(data);
				ClusterRequestObject clusterRequestObject = new ClusterRequestObject();
				Request request = new Request();
				request.setUri(jsonServerRegisterObject.getAddUrl());
				clusterRequestObject.setJsonServerRegisterObject(jsonServerRegisterObject);
				clusterRequestObject.setRequest(request);
				requests.add(clusterRequestObject);
			}
			URI_MAP.put(serviceName, requests);
		}
	}

	@Override
	public void reloadUriMap() {
		
	}

}

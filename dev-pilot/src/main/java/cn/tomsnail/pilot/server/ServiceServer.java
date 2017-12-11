package cn.tomsnail.pilot.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.tomsnail.pilot.model.Consts;
import cn.tomsnail.pilot.model.ServiceProcess;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月6日 下午1:54:39
 * @see 
 */
public class ServiceServer extends ZkServer {

	private static final Map<String,Map<String,List<ServiceProcess>>> nodeServiceSet = new ConcurrentHashMap<String, Map<String,List<ServiceProcess>>>();
	private static final Map<String,List<String>> serviceNodeSet = new ConcurrentHashMap<String, List<String>>();
	private static final Map<String,List<String>> tempNodeServiceSet= new ConcurrentHashMap<String, List<String>>();

	@Override
	public void init() {
		
	}

	@Override
	public void start() {
		
	}

	@Override
	public void stop() {
		
	}

	@Override
	public void notifly(String path, int type) {
		if(type==NotifType.NOTHING) return;
		path = getStandPath(path);
		change(path);
		doCallback(path);	
	}

	private String getStandPath(String path) {
		if(path.split("/").length>1){
			String[] ps = path.split("/");
			path = ps[ps.length-1];
		}
		return path;
	}

	private synchronized void change(String node) {
		List<String> services = getPath(Consts.ZK_ROOT+"/"+node);
		if(services==null||services.size()==0) return;
		Map<String,List<ServiceProcess>> nodeServiceMapList = getNodeServiceMapList(node);
		List<String> nodeServiceList = getNodeServiceList(node);
		List<String> _tempServices = new ArrayList<String>();
		for(String service:services){
			nodeServiceList.remove(service);
			_tempServices.add(service);
			addServiceNode(service,node);
			ServiceProcess serviceProcess = (ServiceProcess) readObject(Consts.ZK_ROOT+"/"+node+"/"+service);
			if(nodeServiceMapList.containsKey(service)){
				nodeServiceMapList.get(service).add(serviceProcess);
			}else{
				List<ServiceProcess> processes = new ArrayList<ServiceProcess>();
				processes.add(serviceProcess);
				nodeServiceMapList.put(service, processes);
			}
		}
		clearDuplicatService(node, nodeServiceList);
		nodeServiceList.clear();
		nodeServiceList.addAll(_tempServices);
	}
	
	private void addServiceNode(String service,String node){
		if(serviceNodeSet.containsKey(service)){
			serviceNodeSet.get(service).add(node);
		}else{
			List<String> list = new ArrayList<String>();
			list.add(node);
			serviceNodeSet.put(service, list);
		}
	}

	private void clearDuplicatService(String node, List<String> nodeServiceList) {
		if(nodeServiceList!=null){
			for(String service:nodeServiceList){
				if(serviceNodeSet.containsKey(service)){
					serviceNodeSet.get(service).remove(node);
				}
			}
		}
	}

	private List<String> getNodeServiceList(String path) {
		List<String> tempServices;
		if(tempNodeServiceSet.containsKey(path)){
			tempServices = tempNodeServiceSet.get(path);
		}else{
			tempServices = new ArrayList<String>();
			tempNodeServiceSet.put(path, tempServices);
		}
		return tempServices;
	}

	private Map<String, List<ServiceProcess>> getNodeServiceMapList(String path) {
		Map<String, List<ServiceProcess>> serviceList;
		if(nodeServiceSet.containsKey(path)){
			serviceList = nodeServiceSet.get(path);
			serviceList.clear();
		}else{
			serviceList = new ConcurrentHashMap<String, List<ServiceProcess>>();
			nodeServiceSet.put(path, serviceList);
		}
		return serviceList;
	}
	
	public List<ServiceProcess> getService(String node,String service){
		if(nodeServiceSet.containsKey(node)){
			return nodeServiceSet.get(node).get(service);
		}
		return null;
	}

}

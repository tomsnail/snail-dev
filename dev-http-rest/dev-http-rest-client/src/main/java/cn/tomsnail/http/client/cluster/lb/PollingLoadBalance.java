package cn.tomsnail.http.client.cluster.lb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tomsnail.http.client.cluster.ClusterRequestObject;
import cn.tomsnail.http.client.core.Request;

/**
 *        轮询负载均衡
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午5:14:52
 * @see 
 */
public class PollingLoadBalance implements ILoadBalance{
	
	/**
	 * 请求对象映射
	 */
	private static final Map<String,PollingObject> POLLING_MAP = new HashMap<String, PollingObject>();

	@Override
	public Request getServiceRequest(String serviceName, List<ClusterRequestObject> requests) {
		if(requests==null||requests.size()<1){
			return null;
		}
		if(!POLLING_MAP.containsKey(serviceName)){
			POLLING_MAP.put(serviceName,  new PollingObject());
		}
		try {
			return requests.get(POLLING_MAP.get(serviceName).count(requests.size())).getRequest().clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *        轮询对象
	 * @author yangsong
	 * @version 0.0.1
	 * @status 正常
	 * @date 2016年9月21日 下午4:17:15
	 * @see 
	 */
	class PollingObject{
		
		private int index = -1;
		
		private byte[] lock = new byte[1];
		
		public  int count(int size){
			synchronized (lock) {
				int _index = index+1;
				if(_index>=size){
					index = -1;
					return 0;
				}else{
					return index = _index;
				}
			}
		}
		
	}

	

}


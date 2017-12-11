package cn.tomsnail.http.client.cluster.lb;

import java.util.List;

import cn.tomsnail.http.client.cluster.ClusterRequestObject;
import cn.tomsnail.http.client.core.Request;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月28日 下午5:58:01
 * @see 
 */
public class HashLoadBalance implements ILoadBalance{

	@Override
	public Request getServiceRequest(String serviceName, List<ClusterRequestObject> requests) {
		return null;
	}

}

package cn.tomsnail.http.client.cluster.lb;

import java.util.List;

import cn.tomsnail.http.client.cluster.ClusterRequestObject;
import cn.tomsnail.http.client.core.Request;

/**
 *        负载均衡
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:16:18
 * @see 
 */
public interface ILoadBalance {

	/**
	 *        获得请求对象
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:16:31
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public Request getServiceRequest(String serviceName,List<ClusterRequestObject> requests);
	
}

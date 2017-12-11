package cn.tomsnail.http.client.cluster;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;
import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;
import cn.tomsnail.http.client.invoker.BasicInvoker;
import cn.tomsnail.http.client.register.ClientRegisterObject;


/**
 *        基础集群服务抽象类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午4:39:06
 * @see 
 */
public abstract class AbstractBasicClusterService implements IClusterService{
	
	protected static final Map<String,List<ClusterRequestObject>> URI_MAP  = new ConcurrentHashMap<String, List<ClusterRequestObject>>();
	
	
	protected BasicInvoker invoker;
	
	/**
	 *        通过服务名，获得请求列表
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:18:16
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public  List<ClusterRequestObject> getServiceRequest(String serviceName, String lbType){
		if(URI_MAP.containsKey(serviceName)){
			return URI_MAP.get(serviceName);
		}else{
			loadUriMap(serviceName);
			return URI_MAP.get(serviceName);
		}
	}
	
	/**
	 *  	加载url映射      
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:18:49
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public abstract void loadUriMap(String serviceName);
	
	
	/**
	 *        获取请求
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:17:50
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public abstract Request getServiceRequestNext(String serviceName,String lbType);
	
	
	/**
	 *        重加载url映射
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:19:02
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public abstract void reloadUriMap();

	public BasicInvoker getInvoker() {
		return invoker;
	}

	public void setInvoker(BasicInvoker invoker) {
		this.invoker = invoker;
	}

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月31日 下午3:50:55
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public Response<ResultData> callback(String serviceName, RequestData requestData,
			ClientRegisterObject clientRegisterObject) throws Exception {
		ResultData resultData = new ResultData();
		Response<ResultData> response = new Response<ResultData>(
				resultData);
		try {
			Request request = getServiceRequestNext(serviceName,clientRegisterObject.getLbType());
			if (request == null) {
				return null;
			}
			request.setBody(requestData);
			invoker.invoker(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}
    

}

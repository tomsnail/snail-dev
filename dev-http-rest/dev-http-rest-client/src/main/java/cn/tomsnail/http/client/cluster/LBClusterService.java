package cn.tomsnail.http.client.cluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;
import cn.tomsnail.http.client.cluster.lb.HashLoadBalance;
import cn.tomsnail.http.client.cluster.lb.ILoadBalance;
import cn.tomsnail.http.client.cluster.lb.LruLoadBalance;
import cn.tomsnail.http.client.cluster.lb.PollingLoadBalance;
import cn.tomsnail.http.client.cluster.lb.RandomLoadBalance;
import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;
import cn.tomsnail.http.client.register.ClientRegisterObject;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月31日 下午3:33:20
 * @see 
 */
public  class LBClusterService extends ZkUriBasicClusterService{
	
	private static ExecutorService executor = Executors.newCachedThreadPool();

	protected Map<String,ILoadBalance> LoadBalance_Map;
	
	public LBClusterService(){
		init();
	}
	
	public LBClusterService(String address) throws Exception{
		super(address);
		init();
	}
	
	private void init(){
		LoadBalance_Map = new HashMap<String, ILoadBalance>();
		LoadBalance_Map.put(LBType.Polling, new PollingLoadBalance());
		LoadBalance_Map.put(LBType.Hash, new HashLoadBalance());
		LoadBalance_Map.put(LBType.Random, new RandomLoadBalance());
		LoadBalance_Map.put(LBType.LRU, new LruLoadBalance());
	}

	@Override
	public Request getServiceRequestNext(String serviceName, String lbType) {
		Request request = null;
		List<ClusterRequestObject> requests = getServiceRequest(serviceName,lbType);
		if(requests==null){
			request =getServiceRequestNext(serviceName,lbType);
		}
		if(requests==null){
			return null;
		}
		if(LoadBalance_Map!=null){
			request = LoadBalance_Map.get(lbType).getServiceRequest(serviceName, requests);
		}else{
			try {
				request = requests.get((int)(System.currentTimeMillis()%requests.size())).getRequest().clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return request;
	}

	@Override
	public ResultData service(final String serviceName, final RequestData requestData,
			final ClientRegisterObject clientRegisterObject) {
		if (serviceName == null || clientRegisterObject == null) {
			return null;
		}
		Response<ResultData> _response = null;
		Future<Response<ResultData>> result = executor.submit(new Callable<Response<ResultData>>() {
					@Override
					public Response<ResultData> call() throws Exception {
						return callback(serviceName, requestData, clientRegisterObject);
					}
				});
		try {
			_response = result.get(clientRegisterObject.getTimeout(),TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (_response == null) {
			return null;
		}
		if (_response.getStatus() == 200) {
			return _response.getBody();
		}
		return null;
	}
	
}

package cn.tomsnail.http.client.cluster.fail;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;
import cn.tomsnail.http.client.cluster.AbstractBasicClusterService;
import cn.tomsnail.http.client.cluster.FailClusterService;
import cn.tomsnail.http.client.core.Response;
import cn.tomsnail.http.client.invoker.BasicInvoker;
import cn.tomsnail.http.client.register.ClientRegisterObject;

public  class RestryService extends FailClusterService {

private static ExecutorService executor = Executors.newFixedThreadPool(5);
	
	/**
	 * 基本执行者
	 */
	protected BasicInvoker invoker;
	
	public RestryService(){
	}
	
	public ResultData service(final AbstractBasicClusterService basicClusterService,final String serviceName, final RequestData requestData,
			final ClientRegisterObject clientRegisterObject) {
		if(serviceName==null||clientRegisterObject==null){
			return null;
		}
		int _count = clientRegisterObject.getRetryCount();
		Response<ResultData> _response = null;
		do{
			Future<Response<ResultData>> result = executor.submit(new Callable<Response<ResultData>>() {
				@Override
				public Response<ResultData> call() throws Exception {
					return basicClusterService.callback(serviceName, requestData, clientRegisterObject);
				}
			});
			try {
				_response = result.get(clientRegisterObject.getTimeout(), TimeUnit.SECONDS);
				if(_response!=null)
					if(_response.getStatus()==200){
						return _response.getBody();
					}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}while(--_count>0);
		return null;
	}

	public BasicInvoker getInvoker() {
		return invoker;
	}

	public void setInvoker(BasicInvoker invoker) {
		this.invoker = invoker;
	}
	
}

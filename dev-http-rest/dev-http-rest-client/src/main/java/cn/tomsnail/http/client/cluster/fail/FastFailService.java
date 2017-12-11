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
import cn.tomsnail.http.client.register.ClientRegisterObject;

/**
 * 
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月31日 下午3:03:15
 * @see
 */
public  class FastFailService extends FailClusterService {

	private static ExecutorService executor = Executors.newFixedThreadPool(5);

	
	public ResultData service(final AbstractBasicClusterService basicClusterService,final String serviceName, final RequestData requestData,
			final ClientRegisterObject clientRegisterObject) {
		if (serviceName == null || clientRegisterObject == null) {
			return null;
		}
		Response<ResultData> _response = null;
		Future<Response<ResultData>> result = executor.submit(new Callable<Response<ResultData>>() {
					@Override
					public Response<ResultData> call() throws Exception {
						return basicClusterService.callback(serviceName, requestData, clientRegisterObject);
					}
				});
		try {
			_response = result.get(clientRegisterObject.getTimeout(),TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (_response == null
				&& clientRegisterObject.getFailType().equals(FailType.FastFail)) {
			return null;
		}
		if (_response.getStatus() == 200) {
			return _response.getBody();
		}
		return null;
	}
	
	


}

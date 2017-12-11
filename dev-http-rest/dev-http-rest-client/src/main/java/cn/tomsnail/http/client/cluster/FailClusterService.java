package cn.tomsnail.http.client.cluster;


import java.util.HashMap;
import java.util.Map;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;
import cn.tomsnail.http.client.cluster.fail.FailType;
import cn.tomsnail.http.client.cluster.fail.FastFailService;
import cn.tomsnail.http.client.cluster.fail.RestryService;
import cn.tomsnail.http.client.register.ClientRegisterObject;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月31日 下午2:43:38
 * @see 
 */
public  class FailClusterService extends LBClusterService{


	private static Map<String, FailClusterService> FAIL_SERVICE_MAP ;
	
	static{
		
		FAIL_SERVICE_MAP = new HashMap<String, FailClusterService>();
		FAIL_SERVICE_MAP.put(FailType.FastFail, new FastFailService());
		FAIL_SERVICE_MAP.put(FailType.Retry, new RestryService());
	}
	
	public FailClusterService() {
		super();
	}
	
	

	public FailClusterService(String address) throws Exception {
		super(address);
	}
	

	@Override
	public ResultData service(String serviceName, RequestData requestData,
			ClientRegisterObject clientRegisterObject) {
		FailClusterService failClusterService =  FAIL_SERVICE_MAP.get(clientRegisterObject.getFailType());
		ResultData resultData = null;
		if(failClusterService!=null){
			resultData =  failClusterService.service(this,serviceName, requestData, clientRegisterObject);
		}
		return resultData;
	}
	
	public ResultData service(AbstractBasicClusterService basicClusterService,String serviceName, RequestData requestData,
			ClientRegisterObject clientRegisterObject) {
		return null;
	}


}

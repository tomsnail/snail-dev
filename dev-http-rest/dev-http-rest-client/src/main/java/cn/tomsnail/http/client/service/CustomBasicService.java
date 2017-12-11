package cn.tomsnail.http.client.service;

import java.util.HashMap;
import java.util.Map;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;

/**
 *        自定义基础服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月5日 下午5:23:37
 * @see 
 */
public class CustomBasicService extends BasicService<ResultData<Map<String,Object>>,RequestData<Map<String,Object>>>{
	
	private String serviceName;
	
	
	public CustomBasicService(String serviceName){
		this.serviceName = serviceName;
	}

	
	public ResultData service(RequestData<Map<String,Object>> requestData) {
		if(requestData.getBody()==null){
			requestData.setBody(new HashMap<String, Object>());
		}
		return this.service(serviceName, requestData);
	}

	
	public ResultData<Map<String,Object>> service(ResultData<Map<String,Object>> r, RequestData<Map<String,Object>> t) {
		return this.service(serviceName, t, r);
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	

	
	
}

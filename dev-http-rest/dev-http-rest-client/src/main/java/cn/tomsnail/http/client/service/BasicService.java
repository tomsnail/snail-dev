package cn.tomsnail.http.client.service;

import java.util.HashMap;
import java.util.Map;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;
import cn.tomsnail.http.client.cluster.FailClusterService;
import cn.tomsnail.http.client.cluster.IClusterService;
import cn.tomsnail.http.client.filter.BasicChainFilter;
import cn.tomsnail.http.client.invoker.BasicInvoker;
import cn.tomsnail.http.client.mapper.DefaultParseMapper;
import cn.tomsnail.http.client.mapper.IParseMapper;
import cn.tomsnail.http.client.register.ClientRegisterObject;
import cn.tomsnail.http.client.request.DefaultRequest;
import cn.tomsnail.http.client.request.IRequest;

/**
 *        客户端基础服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午4:21:18
 * @see 
 */
public class BasicService<T,R> implements IService<T,R> {
	
	/**
	 * 集群服务
	 */
	private IClusterService clusterService;
	
	/**
	 * 注册对象
	 */
	protected ClientRegisterObject clientRegisterObject;


	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:33:07
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception
	 */
	@Override
	public ResultData service(String serviceName,RequestData requestData) {
		if(serviceName==null){
			return null;
		}
		return clusterService.service(serviceName, requestData, clientRegisterObject);
		
	}

	@Override
	public T service(String serviceName,R r, T t) {
		if(serviceName==null||t==null){
			return null;
		}
		return null;
	}

	public IClusterService getClusterService() {
		return clusterService;
	}

	public void setClusterService(IClusterService clusterService) {
		this.clusterService = clusterService;
	}
	
	public ClientRegisterObject getClientRegisterObject() {
		return clientRegisterObject;
	}


	public void setClientRegisterObject(ClientRegisterObject clientRegisterObject) {
		this.clientRegisterObject = clientRegisterObject;
	}

	public static void main(String[] args) throws Exception {
		BasicService<ResultData<Map<String,Object>>, RequestData<Map<String,Object>>> basicService = new BasicService<ResultData<Map<String,Object>>, RequestData<Map<String,Object>>>();
		IClusterService clusterService = new FailClusterService("192.168.169.150:2181");
		BasicInvoker invoker = new BasicInvoker();
		basicService.setClusterService(clusterService);
		BasicChainFilter chainFilter = new BasicChainFilter();
		invoker.setChainFilter(chainFilter);
		IParseMapper parseMapper = new DefaultParseMapper();
		invoker.setMapper(parseMapper);
		clusterService.setInvoker(invoker);
		IRequest request = new DefaultRequest();
		chainFilter.setRequest(request);
		RequestData data =  new RequestData();
		data.setBody(new HashMap<String,Object>());
		for(int i=0;i<1;i++){
			System.out.println(basicService.service("getZkConfigData1",data).getBody());
		}
	}

}

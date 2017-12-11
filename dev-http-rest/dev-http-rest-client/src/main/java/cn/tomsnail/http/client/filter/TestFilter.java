package cn.tomsnail.http.client.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;
import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;
import cn.tomsnail.http.client.mapper.DefaultParseMapper;
import cn.tomsnail.http.client.mapper.IParseMapper;
import cn.tomsnail.http.client.request.DefaultRequest;
import cn.tomsnail.http.client.request.IRequest;

public class TestFilter {

	public static void main(String[] args) {
		
		long d = System.currentTimeMillis();
		for(int i=0;i<1;i++){
			List<IFilter> filters = new ArrayList<IFilter>();
			TestFilter1 filter1 = new TestFilter1();
			TestFilter2 filter2 = new TestFilter2();
			filters.add(filter1);
			filters.add(filter2);
			IRequest request = new DefaultRequest();
			BasicChainFilter basicChainFilter = new BasicChainFilter();
			basicChainFilter.setFilters(filters);
			basicChainFilter.setRequest(request);
			Request<RequestData<Map<String,Object>>> req = new Request<RequestData<Map<String,Object>>>();
			req.setUri("http://192.168.169.88:8080/system/get/config");
			RequestData<Map<String,Object>> data = new RequestData<Map<String,Object>>();
			data.setBody(new HashMap<String, Object>());
			req.setBody(data);
			Response<ResultData<Map<String,Object>>> resp = new Response<ResultData<Map<String,Object>>>();
			basicChainFilter.doFilter(req, resp);
			IParseMapper parseMapper = new DefaultParseMapper();
			parseMapper.parse(resp, ResultData.class);
			System.out.println(resp.getBody().getBody());
		}
	
		
		System.out.println(System.currentTimeMillis()-d);
		
	}
	
}

package cn.tomsnail.http.client.filter;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;

public class TestFilter2 implements IFilter{

	@Override
	public void doFilter(Request request, Response response,
			BasicChainFilter chainFilter) {
		System.out.println("TestFilter2");
		chainFilter.doFilter(request, response);
	}

}

package cn.tomsnail.dubbo.restful.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestFilter implements RestfulFilter{

	@Override
	public boolean filter(HttpServletRequest request, HttpServletResponse response, Object[] args)
			throws RestfulFilterException {
		
		System.out.println(new Date());
		
		return true;
	}

}

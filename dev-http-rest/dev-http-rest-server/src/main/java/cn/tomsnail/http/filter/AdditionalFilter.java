package cn.tomsnail.http.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.dubbo.restful.filter.RestfulFilter;
import cn.tomsnail.dubbo.restful.filter.RestfulFilterException;
import cn.tomsnail.framwork.core.BaseContext;
import cn.tomsnail.framwork.core.BaseContextManager;

@Component
@ComponentScan(basePackages={"cn.tomsnail.dubbo.restful.filter"})
public class AdditionalFilter implements RestfulFilter{

	public boolean filter( HttpServletRequest request,HttpServletResponse response,Object[] args) throws RestfulFilterException{
		String USER_UUID = request.getParameter(BaseContext.USER_UUID);
		BaseContextManager.LOCAL_CONTEXT.set(new BaseContext().addMapValue(BaseContext.USER_UUID, USER_UUID));
		return true;
	}
	
}

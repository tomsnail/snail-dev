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
		
		BaseContext context = new BaseContext()
				.addMapValue(BaseContext.USER_UUID, request.getParameter(BaseContext.USER_UUID))
				.addMapValue(BaseContext.SYSTEM_CODE, request.getParameter(BaseContext.SYSTEM_CODE))
				.addMapValue("DEPT_CODE", request.getParameter("DEPT_CODE"))
				.addMapValue("USER_TYPE", request.getParameter("USER_TYPE"))
				.addMapValue("DEPT_SYS_CODE", request.getParameter("DEPT_SYS_CODE"));
		
		
		BaseContextManager.LOCAL_CONTEXT.set(context);
		return true;
	}
	
}

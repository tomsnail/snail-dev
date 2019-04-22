package cn.tomsnail.snail.ext.security.authority.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.BaseContext;
import cn.tomsnail.snail.core.BaseContextManager;
import cn.tomsnail.snail.core.dubbo.restful.filter.RestfulFilter;
import cn.tomsnail.snail.core.dubbo.restful.filter.RestfulFilterException;



@Component
@ComponentScan(basePackages={"cn.tomsnail.snail.core.dubbo.restful.filter"})
public class AdditionalFilter implements RestfulFilter{

	public boolean filter( HttpServletRequest request,HttpServletResponse response,Object[] args) throws RestfulFilterException{
		
		BaseContext context = new BaseContext()
				.addMapValue(BaseContext.USER_UUID, request.getParameter(BaseContext.USER_UUID))
				.addMapValue(BaseContext.SYSTEM_CODE, request.getParameter(BaseContext.SYSTEM_CODE));
		
		
		BaseContextManager.LOCAL_CONTEXT.set(context);
		return true;
	}
	
}

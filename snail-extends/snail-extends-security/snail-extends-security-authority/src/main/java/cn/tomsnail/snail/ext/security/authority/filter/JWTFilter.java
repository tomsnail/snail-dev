package cn.tomsnail.snail.ext.security.authority.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.dubbo.restful.filter.RestfulFilter;
import cn.tomsnail.snail.core.dubbo.restful.filter.RestfulFilterException;



@Component
@ComponentScan(basePackages={"cn.tomsnail.snail.core.dubbo.restful.filter","cn.tomsnail.snail.ext.security.authority.filter.auth.jwt"})
public class JWTFilter implements RestfulFilter{

	@Override
	public boolean filter(HttpServletRequest request,HttpServletResponse response, Object[] args) throws RestfulFilterException {
		//String signature = request.getHeader("signature");
		return false;
	}

}

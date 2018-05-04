package cn.tomsnail.http.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.dubbo.restful.filter.RestfulFilter;
import cn.tomsnail.dubbo.restful.filter.RestfulFilterException;

@Component
@ComponentScan(basePackages={"cn.tomsnail.dubbo.restful.filter","cn.tomsnail.http.filter.auth.jwt"})
public class JWTFilter implements RestfulFilter{

	@Override
	public boolean filter(HttpServletRequest request,HttpServletResponse response, Object[] args) throws RestfulFilterException {
		String signature = request.getHeader("signature");
		return false;
	}

}

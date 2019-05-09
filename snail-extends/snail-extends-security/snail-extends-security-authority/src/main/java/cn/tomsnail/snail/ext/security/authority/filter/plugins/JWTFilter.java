package cn.tomsnail.snail.ext.security.authority.filter.plugins;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilter;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilterException;
import cn.tomsnail.snail.ext.security.jwt.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;



public class JWTFilter implements RestfulFilter {

	private String authorization = ConfigHelp.getInstance("config").getLocalConfig("system.security.jwt.header","authorization");
	private String secret = ConfigHelp.getInstance("config").getLocalConfig("system.security.jwt.secret","MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=");
	private String alg = ConfigHelp.getInstance("config").getLocalConfig("system.security.jwt.alg","HmacSHA256");

	@Override
	public boolean filter(HttpServletRequest request,HttpServletResponse response, Object[] args) throws RestfulFilterException {
		String token = request.getHeader(authorization); //获取请求传来的token
		Claims claims = JwtHelper.verifyJwt(token,secret,alg); //验证token
		if (claims != null) {
			return true;
		}
		return false;
	}

}

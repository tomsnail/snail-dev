package cn.tomsnail.snail.ext.security.authority.filter.plugins;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilter;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.ext.security.token.TokenFactory;

   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年9月27日 下午1:54:15
	* @see 
	*/     
@Component
@ComponentScan(basePackages={"cn.tomsnail.snail.core.dubbo.restful.filter","cn.tomsnail.snail.ext.security.authority.filter.auth.cookie"})
public class SimpleSignCookieFilter implements RestfulFilter {
	
	@Autowired(required=false)
	@Qualifier("tokenFactory")
	private TokenFactory tokenFactory;

	@Override
	public boolean filter(HttpServletRequest request,HttpServletResponse response,Object[] args) throws RestfulFilterException {
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies==null||cookies.length==0){
			return false;
		}
		
		String ticket = "";		
		
		for(Cookie _cookie:cookies){
			if(_cookie.getName().equals("ts_ticket_uuid")){
				ticket = _cookie.getValue();
			}
		}
		
		if(StringUtils.isBlank(ticket)){
			return false;
		}
		
		return tokenFactory.validaToken(ticket, (token)->{
			return true;
		});	
	}
}

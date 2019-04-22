package cn.tomsnail.snail.ext.security.authority.filter;


import java.util.Enumeration;
import java.util.UUID;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.BaseContext;
import cn.tomsnail.snail.core.BaseContextManager;
import cn.tomsnail.snail.core.dubbo.restful.filter.RestfulFilter;
import cn.tomsnail.snail.core.dubbo.restful.filter.RestfulFilterException;
import cn.tomsnail.snail.core.util.math.SignUtil;
import cn.tomsnail.snail.core.util.math.encrypt.MD5Util;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.ext.security.authority.AuthoritySignatureTypePolicy;
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
@ComponentScan(basePackages={"cn.tomsnail.snail.core.dubbo.restful.filter","cn.tomsnail.snail.ext.security.authority.filter.auth.header"})
public class SignHeaderFilter implements RestfulFilter{
	
	@Autowired(required=false)
	@Qualifier("tokenFactory")
	private TokenFactory tokenFactory;

	@Override
	public boolean filter(HttpServletRequest request,HttpServletResponse response,Object[] args) throws RestfulFilterException {
		String ticket = request.getHeader("ts_ticket_uuid");		
		String timestamp = request.getHeader("ts_timestamp");
		//int expire = request.getIntHeader("ts_expire");
		String signature  = request.getHeader("ts_signature");
		String noncestr  = request.getHeader("ts_noncestr");
		int signatureType  = request.getIntHeader("ts_signature_type");//200 简单 300 一次一码  100  初始化
		//String tokenStr  = request.getHeader("ts_token");
		int signatureSize = 0;
		if(StringUtils.isBlank(ticket)||StringUtils.isBlank(signature)){
			return false;
		}
		
		if(signatureType==0){
			return true;
		}
		if(signatureType>=AuthoritySignatureTypePolicy.EVENY_TIMEOUT){
			signatureSize = 3;
		}else if(signatureType>=AuthoritySignatureTypePolicy.LONG_TIMEOUT){
			signatureSize = 2;
		}
		String[] _params = null;
		boolean isAllParamValitor = !(signatureType==AuthoritySignatureTypePolicy.LONG_TIMEOUT||signatureType==AuthoritySignatureTypePolicy.EVENY_TIMEOUT);
		if(request.getMethod().equalsIgnoreCase("GET")){
			if(isAllParamValitor){
				signatureSize = request.getParameterMap().size()+signatureSize;
			}
			_params = new String[signatureSize];
			if(isAllParamValitor){
				Enumeration<String> es = request.getParameterNames();
				int i=0;
				while(es.hasMoreElements()){
					String name = es.nextElement();
					_params[i++]=request.getParameter(name);
				}
			}
		}else{
			_params = new String[signatureSize];
		}
		String[] params = _params;
		return tokenFactory.validaToken(ticket, (token)->{
			//params[params.length-1] = token.getSign();
			params[params.length-1] = timestamp;
			params[params.length-2] = noncestr;
			if(signatureType>=300){
				params[params.length-3] = token.getToken();
			}
			boolean r = SignUtil.validSignHmac(signature,token.getSign(), params);
			if(r&&signatureType>=AuthoritySignatureTypePolicy.EVENY_TIMEOUT){
				token.setToken(MD5Util.md5Encode(token.getToken().toString()+System.currentTimeMillis()));
				token.setSign(UUID.randomUUID().toString());
				tokenFactory.updateToken(token);
				response.setHeader("ts_token", token.getToken());
				response.setHeader("ts_signature", token.getSign());
			}
			if(r){
				BaseContext baseContext = new BaseContext();
				baseContext.setInfo(token.getInfo());
				BaseContextManager.LOCAL_CONTEXT.set(baseContext);
			}
			return r;
		});	
	}
}

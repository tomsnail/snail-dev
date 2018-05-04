package cn.tomsnail.http.filter.auth.header;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tomsnail.auth.authority.AuthoritySignatureTypePolicy;
import cn.tomsnail.auth.token.CacheData;
import cn.tomsnail.auth.token.TokenFactory;
import cn.tomsnail.http.filter.auth.Authority;

@Service
public class AuthorityHeaderImpl implements Authority{
	
	@Autowired
	private TokenFactory tokenFactory;

	@Override
	public void doAuthority(HttpServletRequest httpServletRequest,HttpServletResponse response, String info,int expire) {
		this.doAuthority(httpServletRequest, response, info, expire,AuthoritySignatureTypePolicy.LONG_TIMEOUT);
	}

	@Override
	public void clearAuthority(HttpServletRequest httpServletRequest,HttpServletResponse response, String info) {
		tokenFactory.clearToken(info);
		response.setHeader("ts_ticket_uuid", "");
		response.setHeader("ts_timestamp", "");
		response.setHeader("ts_expire", "");
		response.setHeader("ts_signature", "");
		response.setHeader("ts_signature_type", "");
		response.setHeader("ts_token", "");
	}

	@Override
	public void doAuthority(HttpServletRequest httpServletRequest,HttpServletResponse response, String info, int expire,int authoritySignatureTypePolicy) {
		CacheData cacheData = tokenFactory.createToken(info, expire);
		response.addHeader("ts_ticket_uuid", cacheData.getTicket());
		response.addHeader("ts_timestamp",  System.currentTimeMillis()+"");
		response.addIntHeader("ts_expire", expire);
		response.addHeader("ts_signature", cacheData.getSign());
		response.addIntHeader("ts_signature_type",authoritySignatureTypePolicy);
		response.addHeader("ts_token", cacheData.getToken());
		
	}

}

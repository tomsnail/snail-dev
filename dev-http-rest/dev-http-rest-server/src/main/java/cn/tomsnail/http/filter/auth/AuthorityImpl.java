package cn.tomsnail.http.filter.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tomsnail.auth.authority.AuthoritySignatureTypePolicy;
import cn.tomsnail.auth.token.CacheData;
import cn.tomsnail.auth.token.TokenFactory;

@Service
public class AuthorityImpl implements Authority{
	
	@Autowired
	private TokenFactory tokenFactory;

	@Override
	public void doAuthority(HttpServletRequest httpServletRequest,HttpServletResponse response, String info,int expire) {
		CacheData cacheData = tokenFactory.createToken(info, expire);
		response.addHeader("ts_ticket_uuid", cacheData.getTicket());
		response.addHeader("ts_timestamp", "0");
		response.addIntHeader("ts_expire", expire);
		response.addHeader("ts_signature", cacheData.getSign());
		response.addIntHeader("ts_signature_type", AuthoritySignatureTypePolicy.INIT_TIMEOUT);
		response.addHeader("ts_token", cacheData.getToken());
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

}

package cn.tomsnail.http.filter.auth.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tomsnail.auth.authority.AuthoritySignatureTypePolicy;
import cn.tomsnail.auth.token.CacheData;
import cn.tomsnail.auth.token.TokenFactory;
import cn.tomsnail.http.filter.auth.Authority;

@Service
public class AuthorityCookieImpl implements Authority{
	
	@Autowired
	private TokenFactory tokenFactory;

	@Override
	public void doAuthority(HttpServletRequest httpServletRequest,HttpServletResponse response, String info, int expire) {
		this.doAuthority(httpServletRequest, response, info, expire,AuthoritySignatureTypePolicy.LONG_TIMEOUT);


	}

	@Override
	public void doAuthority(HttpServletRequest httpServletRequest,HttpServletResponse response, String info, int expire,int authoritySignatureTypePolicy) {
		CacheData cacheData = tokenFactory.createToken(info, expire);
		Cookie ts_ticket_uuid = new Cookie("ts_ticket_uuid", cacheData.getTicket());
		ts_ticket_uuid.setHttpOnly(true);
		ts_ticket_uuid.setMaxAge(expire);
		Cookie ts_timestamp = new Cookie("ts_timestamp", System.currentTimeMillis()+"");
		ts_timestamp.setMaxAge(expire);
		Cookie ts_expire = new Cookie("ts_expire", expire+"");
		ts_expire.setMaxAge(expire);
		Cookie ts_signature = new Cookie("ts_signature", cacheData.getSign());
		ts_signature.setMaxAge(expire);
		Cookie ts_signature_type = new Cookie("ts_signature_type", authoritySignatureTypePolicy+"");
		ts_signature_type.setMaxAge(expire);
		Cookie ts_token = new Cookie("ts_token", cacheData.getToken());
		ts_token.setHttpOnly(true);
		ts_token.setMaxAge(expire);
		response.addCookie(ts_ticket_uuid);
		response.addCookie(ts_timestamp);
		response.addCookie(ts_expire);
		response.addCookie(ts_signature);
		response.addCookie(ts_signature_type);
		response.addCookie(ts_token);
	}

	@Override
	public void clearAuthority(HttpServletRequest httpServletRequest,HttpServletResponse response, String info) {
		tokenFactory.clearToken(info);
		Cookie ts_ticket_uuid = new Cookie("ts_ticket_uuid", "");
		ts_ticket_uuid.setMaxAge(1);
		Cookie ts_timestamp = new Cookie("ts_timestamp", System.currentTimeMillis()+"");
		ts_timestamp.setMaxAge(1);
		Cookie ts_expire = new Cookie("ts_expire", "");
		ts_expire.setMaxAge(1);
		Cookie ts_signature = new Cookie("ts_signature", "");
		ts_signature.setMaxAge(1);
		Cookie ts_signature_type = new Cookie("ts_signature_type","");
		ts_signature_type.setMaxAge(1);
		Cookie ts_token = new Cookie("ts_token", "");
		ts_token.setMaxAge(1);
		response.addCookie(ts_ticket_uuid);
		response.addCookie(ts_timestamp);
		response.addCookie(ts_expire);
		response.addCookie(ts_signature);
		response.addCookie(ts_signature_type);
		response.addCookie(ts_token);
		
	}

}

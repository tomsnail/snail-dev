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
import cn.tomsnail.util.configfile.ConfigHelp;

@Service
public class AuthorityCookieImpl implements Authority{
	
	private static final String DOMAIN = ConfigHelp.getInstance("config").getLocalConfig("auth.cookie.domain", ".tomsnail.tech");
	
	private static final String PATH = ConfigHelp.getInstance("config").getLocalConfig("auth.cookie.path", "/");
	
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
		ts_ticket_uuid.setPath(PATH);
		ts_ticket_uuid.setDomain(DOMAIN);
		Cookie ts_timestamp = new Cookie("ts_timestamp", System.currentTimeMillis()+"");
		ts_timestamp.setMaxAge(expire);
		ts_timestamp.setPath(PATH);
		ts_timestamp.setDomain(DOMAIN);
		Cookie ts_expire = new Cookie("ts_expire", expire+"");
		ts_expire.setMaxAge(expire);
		ts_expire.setPath(PATH);
		ts_expire.setDomain(DOMAIN);
		Cookie ts_signature = new Cookie("ts_signature", cacheData.getSign());
		ts_signature.setMaxAge(expire);
		ts_signature.setPath(PATH);
		ts_signature.setDomain(DOMAIN);
		Cookie ts_signature_type = new Cookie("ts_signature_type", authoritySignatureTypePolicy+"");
		ts_signature_type.setMaxAge(expire);
		ts_signature_type.setPath(PATH);
		ts_signature_type.setDomain(DOMAIN);
		Cookie ts_token = new Cookie("ts_token", cacheData.getToken());
		ts_token.setHttpOnly(true);
		ts_token.setPath(PATH);
		ts_token.setDomain(DOMAIN);
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

package cn.tomsnail.snail.ext.security.authority.filter.plugins;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.math.encrypt.HmacSha1Util;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilter;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilterException;




public class SimpleFilter implements RestfulFilter {
	
	
	private String _token = ConfigHelp.getInstance("config").getLocalConfig("system.security.simple.token","1qaz2wsx3EDC4RFV!@#");

	private String _sign = ConfigHelp.getInstance("config").getLocalConfig("system.security.simple.sign","snail-signature");

	private String _timestamp = ConfigHelp.getInstance("config").getLocalConfig("system.security.simple.timestamp","snail-timestamp");

	@Override
	public boolean filter(HttpServletRequest request,HttpServletResponse response, Object[] args)throws RestfulFilterException {
		
		
		String signature = request.getHeader(_sign);
		
		String timestamp = request.getHeader(_timestamp);
		
		if(StringUtils.isAnyBlank(signature,timestamp,_token)){
			return false;
		}
		
		String _signature = HmacSha1Util.getSoureSignature(timestamp.getBytes(), _token.getBytes());
		
		
		return _signature.equals(signature);
	}


	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		this._token = token;
	}

	public String getSign() {
		return _sign;
	}

	public void setSign(String sign) {
		this._sign = sign;
	}

	public String getTimestamp() {
		return _timestamp;
	}

	public void setTimestamp(String timestamp) {
		this._timestamp = timestamp;
	}
}

package cn.tomsnail.snail.ext.security.token;

import java.util.UUID;

import com.alibaba.fastjson.JSON;

import cn.tomsnail.snail.core.util.math.encrypt.HmacSha1Util;
import cn.tomsnail.snail.core.util.math.encrypt.MD5Util;
import cn.tomsnail.snail.ext.cache.annotation.CacheClassd;
import cn.tomsnail.snail.ext.cache.annotation.CacheConfig;
import cn.tomsnail.snail.ext.cache.core.ICache;



@CacheClassd
public class TokenFactory {
	
	private static int DEFAULT_EXPIRE = 7200;
	private static TicketSum DEFAULT_SUM = null;
	private static byte[] KEY = ("TOKENFACTORY001").getBytes();
	
	@CacheConfig(cachedType="${token.cachedType}",url="${token.url}",port="${token.port}",name="${token.name}",expire="${token.expire}",timeout="${token.timeout}",username="${token.username}",password="${token.password}")
	private ICache cache;
	
	public  void init(int expire,TicketSum tokenSum,byte[] key){
		if(expire>=0){
			DEFAULT_EXPIRE = expire;
		}
		if(tokenSum!=null){
			DEFAULT_SUM = tokenSum;
		}
		if(key!=null&&key.length>0){
			KEY = key;
		}
	}
	
	public  CacheData createToken(String info){
		return createToken(info,DEFAULT_EXPIRE,DEFAULT_SUM);
	}
	
	public  CacheData createToken(String info,int expire){
		return createToken(info,expire,DEFAULT_SUM);
	}
	
	
	public  CacheData createToken(String info,TicketSum tokenSum){
		return createToken(info,DEFAULT_EXPIRE,tokenSum);
	}
	
	public  CacheData createToken(String info,int expire,TicketSum tokenSum){
		if(tokenSum==null){
			tokenSum = (i)->(HmacSha1Util.getSignature(info.getBytes(), KEY));
		}
		CacheData token = sum(info,tokenSum);
		String sign = UUID.randomUUID().toString().replace("-", "");
		token.setToken(MD5Util.md5Encode(sign+info));
		token.setSign(sign);
		token.setExpire(expire);
		token.setId(token.getTicket());
		if(cache!=null&&expire>0){
			String jsonStr = JSON.toJSONString(token);
			cache.set(token.getTicket(), jsonStr, expire);
		}
		return token;
	}
	
	private static CacheData sum(String info,TicketSum ticketSum){
		return  new CacheData(info,ticketSum.sum(info));
	}
	
	public  boolean validaToken(String ticket,TokenValitor tokenValitor){
		if(cache==null||!cache.isExits(ticket)){
			return false;
		}
		return tokenValitor.valitor(JSON.parseObject(cache.get(ticket).toString(), CacheData.class));
	}
	
	public  void updateToken(CacheData t){
		if(cache!=null){
			cache.set(t.getTicket(), JSON.toJSONString(t), t.getExpire());
		}
	}
	
	public  void clearToken(String info){
		try {
			if(cache!=null){
				cache.remove(HmacSha1Util.getSignature(info.getBytes(), KEY));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

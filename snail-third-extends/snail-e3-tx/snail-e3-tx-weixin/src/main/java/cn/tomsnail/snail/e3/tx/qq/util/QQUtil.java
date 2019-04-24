package cn.tomsnail.snail.e3.tx.qq.util;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;

import cn.tomsnail.snail.e3.tx.weixin.util.common.HttpTool;

public class QQUtil {

	public static Map<String,String> getUserInfo(String token,String openid,String appId){
		String userUrl = "https://graph.qq.com/user/get_user_info?access_token="+token+"&oauth_consumer_key="+appId+"&openid="+openid+"";
		String content = HttpTool.get(userUrl);
		System.out.println(content);
		Map userMap = Json.fromJson(Map.class, content);
		return userMap;
	}
	
	public static Map<String,String> getOpenId(String code,String appId,String appSecrt,String redirectUri){
		String tokenUrl = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="+appId+"&client_secret="+appSecrt+"&code="+code+"&redirect_uri="+redirectUri;
		String content = HttpTool.get(tokenUrl);
		System.out.println(content);
		String[] cs = content.split("&");
		Map<String,String> tokenMap = new HashMap<String, String>();
		for(String c:cs){
			tokenMap.put(c.split("=")[0], c.split("=")[1]);
		}
		String access_token = tokenMap.get("access_token")+"";
		String openIdUrl = "https://graph.qq.com/oauth2.0/me?access_token="+access_token;
		content = HttpTool.get(openIdUrl);
		System.out.println(content);
		Map openIdMap = Json.fromJson(Map.class, content.replace("callback( ", "").replace(" )", ""));
		String openId = openIdMap.get("openid")+"";
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("access_token", access_token);
		resultMap.put("openid", openId);
		return resultMap;
	}
	
}

package cn.tomsnail.weixin.util;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;

import cn.tomsnail.weixin.util.common.HttpTool;

public class WeixinUtil {

	public static Map<String,Object> getUserInfo(String token,String openid,String appId){
		String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+token+"&openid="+openid+"";
		String content = HttpTool.get(userUrl);
		System.out.println(content);
		Map userMap = Json.fromJson(Map.class, content);
		return userMap;
	}
	
	public static Map<String,String> getOpenId(String code,String appId,String appSecrt,String redirectUri){
		String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&appid="+appId+"&secret="+appSecrt+"&code="+code;
		String content = HttpTool.get(tokenUrl);
		System.out.println(content);
		Map tokenMap = Json.fromJson(Map.class, content);
		String openId = tokenMap.get("openid")+"";
		String access_token = tokenMap.get("access_token")+"";
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("access_token", access_token);
		resultMap.put("openid", openId);
		resultMap.put("scope", tokenMap.get("scope")+"");
		return resultMap;
	}
	
}

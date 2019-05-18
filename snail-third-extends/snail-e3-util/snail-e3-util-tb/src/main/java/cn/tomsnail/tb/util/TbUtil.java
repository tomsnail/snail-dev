package cn.tomsnail.tb.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TbUtil {

	private static final Logger logger = LoggerFactory.getLogger(TbUtil.class);
	
	
	private static final String apiUrl = "https://api.teambition.com";

	private static final String key = ConfigHelp.getInstance("tb").getLocalConfig("tb.key", "ef3ace00-0dbc-11e6-b4d3-69a250d7c1af");// ef3ace00-0dbc-11e6-b4d3-69a250d7c1af

	private static final String secert = ConfigHelp.getInstance("tb").getLocalConfig("tb.secert", "367dbd77-b684-4fcb-8f75-835da50c3bd5");// 367dbd77-b684-4fcb-8f75-835da50c3bd5

	private static final String callbackUrl = ConfigHelp.getInstance("tb").getLocalConfig("tb.callbackUrl","http://1.testtbys.applinzi.com/tbController/callback.do");

	private static final String code = "TJFwwj4tSKxMojP8knyXR4";
	
	public static String token = "";

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
	}
	
	
	private static String getMyInfo(){
		String url = apiUrl+"/api/users/me";
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("", value)
		return sendGeta(url);
	}
	
	public static String checkToken(){
		String url = "https://api.teambition.com/api/applications/"+key+"/tokens/check";
		return sendGeta(url);
	}
	
	public static String creatTask(String taskId,String userId,String content,String note) throws Exception{
		//57219892f939662265bb2485
		String url = apiUrl+"/api/tasks";
		Map<String,String> map  = new HashMap<String, String>();
		map.put("content", content);
		map.put("_tasklistId", taskId);
		map.put("_executorId", userId);
		map.put("note", note);
		return sendPosta(url,map);
	}
	
	public static String creatTaskWithInvolveMembers(String taskId,String userId,String content,String note,String involveMembers) throws Exception{
		//57219892f939662265bb2485
		String url = apiUrl+"/api/tasks";
		Map<String,String> map  = new HashMap<String, String>();
		map.put("content", content);
		map.put("_tasklistId", taskId);
		map.put("_executorId", userId);
		map.put("note", note);
		if(StringUtils.isNotBlank(involveMembers)){
			String[] ims = involveMembers.split(",");
			int count = 0;
			for(String im:ims){
				if(StringUtils.isNotBlank(im)){
					map.put("involveMembers["+count+"]", im);
					count++;
				}
			}
		}
		return sendPosta(url,map);
	}

	
	private static String getMyTeam(){
		String url = apiUrl+"/api/tasklists/57219892f939662265bb2485";
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("", value)
		return sendGeta(url);
	}
	
	
	
	
	public static String authorize() {
		Map<String, Object> m1 = new HashMap<String, Object>();
		m1.put("client_id", key);
		m1.put("redirect_uri", callbackUrl);
		String url1 = "https://account.teambition.com/oauth2/authorize?response_type=code&client_id="+key+"&redirect_uri="+callbackUrl+"";
		System.out.println(url1);
		//JSONObject json1 = JSONObject.fromObject(m1);
		JSONObject jsonObject = null;
		try {
			//logger.info(json1.toString());
			jsonObject = HttpsUtil.httpsRequest(url1, "GET", null);
			logger.info("",jsonObject);
			if (jsonObject != null) {
				if (0 == jsonObject.getInt("errcode")) {
				} else {
				}
			} else {
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	public static String getToken(String code,String token) {
		if(!StringUtils.isBlank(code)){
			Map<String, Object> m1 = new HashMap<String, Object>();
			m1.put("client_id", key);
			m1.put("client_secret", secert);
			m1.put("code", code);
			m1.put("grant_type", "code");
			String url1 = "https://account.teambition.com/oauth2/access_token";
			JSONObject json1 = JSONObject.fromObject(m1);
			String jsonObjectStr = null;
			try {
				logger.info(json1.toString());
				jsonObjectStr = sendPost1(url1,code,key,secert);
				JSONObject object = JSONObject.fromObject(jsonObjectStr);
				token = object.getString("access_token");
				logger.info(jsonObjectStr);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		if(!StringUtils.isBlank(token)){
			TbUtil.token = token;
		}
		return TbUtil.token;
	}

	private static String sendPost(String url, String param) {
		DefaultHttpClient client = new DefaultHttpClient();
		// client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,
		// true);
		HttpPost httpost = new HttpPost(url); // 设置响应头信息
		httpost.addHeader("Connection", "keep-alive");
		httpost.addHeader("Accept", "*/*");
		httpost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httpost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpost.addHeader("Cache-Control", "max-age=0");

		String result = "";
		try {
			StringEntity entity = new StringEntity(param, "UTF-8");
			httpost.setEntity(entity);
			HttpResponse response = null;
			try {
				response = client.execute(httpost);
			} catch (Exception e) {
				logger.error("", e);
				try {
					response = client.execute(httpost);
				} catch (Exception e2) {
					logger.error("", e2);
				}
			}
			logger.error("",response);
			if (response != null) {
				result = EntityUtils.toString(response.getEntity(), "UTF-8");
			}

		} catch (Exception e) {
			logger.error("", e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			if (httpost != null) {
				httpost.abort();
			}
		}
		return result;
	}

	private static String sendPost1(String url,String code,String key,String secret) {
		String responseDate = "" ;
		PostMethod postMethod = new PostMethod(
				url);
		postMethod.addParameter("grant_type", "code");
		postMethod.addParameter("code", code);
		postMethod.addParameter("client_id", key);
		postMethod.addParameter("client_secret", secret);
		postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpClient client = new HttpClient();
		try {
			client.executeMethod(postMethod);
			responseDate = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseDate;
	}
	
	private static String sendPost(String url,Map<String,String> map) {
		String responseDate = "" ;
		PostMethod postMethod = new PostMethod(
				url);
		
		if(map!=null){
			Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String,String> entry = it.next();
				postMethod.addParameter(entry.getKey(),entry.getValue());
			}
		}
		postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpClient client = new HttpClient();
		try {
			client.executeMethod(postMethod);
			responseDate = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseDate;
	}
	
	private static String sendPosta(String url,Map<String,String> map) throws Exception {
		String responseDate = "" ;
		PostMethod postMethod = new PostMethod(
				url);
		
		if(map!=null){
			Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String,String> entry = it.next();
				postMethod.addParameter(entry.getKey(),entry.getValue());
			}
		}
		postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		postMethod.addRequestHeader("Authorization", "OAuth2 "+token);
		HttpClient client = new HttpClient();
		try {
			client.executeMethod(postMethod);
			responseDate = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			throw e;
		}
		return responseDate;
	}
	
	private static String sendGeta(String url) {
		logger.info("url:"+url);
		String responseDate = "" ;
		GetMethod method = new GetMethod(url);
		
		//postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		method.addRequestHeader("Authorization", "OAuth2 "+token);
		HttpClient client = new HttpClient();
		try {
			client.executeMethod(method);
			responseDate = method.getResponseBodyAsString();
			logger.info(responseDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseDate;
	}
	
	public static List<Map<String,String>> getUserInfo(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String orgUrl = apiUrl+"/api/organizations";;
		String orgUserUrl = apiUrl +"/api/organizations/xxxx/members";
		List<Map<String,String>> orgUsers = getUsers(orgUrl,orgUserUrl);
		String teamUrl = apiUrl+"/api/teams";
		String teamUserUrl = apiUrl +"/api/teams/xxxx/members";
		List<Map<String,String>> teamUsers = getUsers(teamUrl,teamUserUrl);
		String projectUrl = apiUrl+"/api/projects";
		String projectUserUrl = apiUrl +"/api/projects/xxxx/members";
		List<Map<String,String>> projectUsers = getUsers(projectUrl,projectUserUrl);
		list.addAll(orgUsers);
		list.addAll(teamUsers);
		list.addAll(projectUsers);
		return list;
	}
	
	public static List<Map<String,String>> getTask(){
		
		String projectUrl = apiUrl+"/api/projects";
		String projectTaskUrl = apiUrl +"/api/projects/xxxx/tasks";
		List<Map<String,String>> projectTask = getTasks(projectUrl,projectTaskUrl);
		logger.info("",projectTask);
		return projectTask;
	}
	
	private static List<Map<String,String>> getTasks(String url,String projectTaskUrl){
		 List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
		try {
			String res = sendGeta(url);
			JSONArray jsonArray = JSONArray.fromObject(res);
			if(jsonArray!=null&&jsonArray.size()>0){
				for(int i=0;i<jsonArray.size();i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					if(jsonObject!=null){
						String tid = jsonObject.getString("_id");
						String name = jsonObject.getString("name");
						String _res = sendGeta(projectTaskUrl.replace("xxxx", tid));
						JSONArray _jsonArray = JSONArray.fromObject(_res);
						if(_jsonArray!=null&&_jsonArray.size()>0){
							for(int _i=0;_i<1;_i++){
								try {
									JSONObject _jsonObject = _jsonArray.getJSONObject(_i);
									String _tasklistId = _jsonObject.getString("_tasklistId");
									Map<String,String> map = new HashMap<String,String>();
									map.put("name", name);
									map.put("taskid", _tasklistId);
									map.put("id", tid);
									maps.add(map);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maps;
	}	
	
	private static List<Map<String,String>> getUsers(String url,String userUrl){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try {
			String res = sendGeta(url);
			JSONArray jsonArray = JSONArray.fromObject(res);
			if(jsonArray!=null&&jsonArray.size()>0){
				for(int i=0;i<jsonArray.size();i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					if(jsonObject!=null){
						String tid = jsonObject.getString("_id");
						String _res = sendGeta(userUrl.replace("xxxx", tid));
						JSONArray _jsonArray = JSONArray.fromObject(_res);
						if(_jsonArray!=null&&_jsonArray.size()>0){
							for(int _i=0;_i<_jsonArray.size();_i++){
								try {
									JSONObject _jsonObject = _jsonArray.getJSONObject(_i);
									String email = _jsonObject.getString("email");
									String _userId = _jsonObject.getString("_userId");
									Map<String,String> map = new HashMap<String,String>();
									map.put("email", email);
									map.put("tbId", _userId);
									list.add(map);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}	
	
	
}

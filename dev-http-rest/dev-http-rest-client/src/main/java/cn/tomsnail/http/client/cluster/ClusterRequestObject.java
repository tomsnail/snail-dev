package cn.tomsnail.http.client.cluster;


import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.register.core.JsonServerRegisterObject;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月31日 下午4:05:39
 * @see 
 */
public class ClusterRequestObject {

	private Request request;
	
	private JsonServerRegisterObject jsonServerRegisterObject;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public JsonServerRegisterObject getJsonServerRegisterObject() {
		return jsonServerRegisterObject;
	}

	public void setJsonServerRegisterObject(
			JsonServerRegisterObject jsonServerRegisterObject) {
		this.jsonServerRegisterObject = jsonServerRegisterObject;
	}
	
	
	
}

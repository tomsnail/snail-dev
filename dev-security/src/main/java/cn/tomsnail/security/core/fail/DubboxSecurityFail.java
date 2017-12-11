package cn.tomsnail.security.core.fail;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.tomsnail.framwork.http.CommonMessage;
import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;

/**
 *        dubbo 失败处理
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午6:27:20
 * @see 
 */
@Path("security")
public class DubboxSecurityFail implements IDubboxSecurityFail {

	@POST
	@Path("unauthorized")
	@Produces({"application/json; charset=UTF-8"})
	@Consumes({"application/json; charset=UTF-8"})
	public ResultData<Map<String, String>> unauthorized(
			RequestData<Map<String, String>> request) {
		ResultData<Map<String, String>> resultData = new ResultData<Map<String,String>>();
		resultData.setStatus(CommonMessage.UNAUTHORIZED);
		return resultData;
	}

	
}

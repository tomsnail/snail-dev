package cn.tomsnail.snail.ext.security.core.fail;

import java.util.Map;

import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;



/**
 *        dubbo失败处理
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午6:27:40
 * @see 
 */
public interface IDubboxSecurityFail {

//unauthorized
	
	public ResultData<Map<String,String>> unauthorized(RequestData<Map<String,String>> request);
}

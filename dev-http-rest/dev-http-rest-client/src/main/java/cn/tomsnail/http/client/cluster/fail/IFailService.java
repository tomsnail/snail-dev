package cn.tomsnail.http.client.cluster.fail;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;
import cn.tomsnail.http.client.register.ClientRegisterObject;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月31日 下午3:14:26
 * @see 
 */
public interface IFailService {

	public ResultData service(String serviceName, RequestData requestData,
			ClientRegisterObject clientRegisterObject) ;
	
}

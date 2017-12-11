package cn.tomsnail.http.client.cluster;

import cn.tomsnail.framwork.http.RequestData;
import cn.tomsnail.framwork.http.ResultData;
import cn.tomsnail.http.client.invoker.BasicInvoker;
import cn.tomsnail.http.client.register.ClientRegisterObject;

/**
 *        集群服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午12:06:07
 * @see 
 */
public interface IClusterService {

	
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月31日 下午2:42:07
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public ResultData service(String serviceName,RequestData requestData,ClientRegisterObject clientRegisterObject) ;
	
	public BasicInvoker getInvoker() ;

	public void setInvoker(BasicInvoker invoker);
	
}

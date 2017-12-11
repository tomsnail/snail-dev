package cn.tomsnail.http.client.invoker;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;

/**
 *        监控插件
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月29日 上午11:49:30
 * @see 
 */
public class MonitorInvoker implements IInvoker{
	
	
	@Override
	public boolean invoker(Request request, Response response) {
		
		return true;
	}

}

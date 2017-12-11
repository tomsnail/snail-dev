package cn.tomsnail.http.client.invoker;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;

/**
 *        直接返回
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月25日 下午1:43:17
 * @see 
 */
public class NullInvoker  implements IInvoker{

	@Override
	public boolean invoker(Request request, Response response) {
		response.setStatus(200);
		return false;
	}

}

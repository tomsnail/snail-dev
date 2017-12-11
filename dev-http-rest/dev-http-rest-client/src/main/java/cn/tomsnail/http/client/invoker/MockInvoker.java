package cn.tomsnail.http.client.invoker;

import cn.tomsnail.http.client.core.MockRequest;
import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;

/**
 *        本地Mock数据返回,规则是body中设置具体对象，在这儿依据类型随机生成数据
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月25日 上午11:35:22
 * @see 
 */
public class MockInvoker implements IInvoker{

	@Override
	public boolean invoker(Request request, Response response) {
		if(response==null){
			return false;
		}
		if(request instanceof MockRequest){
			MockRequest mockRequest = (MockRequest) request;
			response.setBody(mockRequest.getQ());
		}else{
			return true;
		}
		response.setStatus(200);
		return false;
	}

}

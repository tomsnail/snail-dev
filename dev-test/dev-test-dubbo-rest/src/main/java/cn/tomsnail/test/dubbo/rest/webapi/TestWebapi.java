package cn.tomsnail.test.dubbo.rest.webapi;


import cn.tomsnail.test.dubbo.rest.mo.RequestData;
import cn.tomsnail.test.dubbo.rest.mo.ResponseData;

public interface TestWebapi {
	
	public ResponseData testGet(String no);
	
	public ResponseData testPost(RequestData requestData);
	
	

}

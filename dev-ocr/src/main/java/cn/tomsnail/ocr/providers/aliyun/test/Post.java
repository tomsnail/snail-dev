package cn.tomsnail.ocr.providers.aliyun.test;

import com.alibaba.cloudapi.sdk.model.ApiCallback;

public interface Post {
	
	public void post(byte[] body , ApiCallback callback);

}

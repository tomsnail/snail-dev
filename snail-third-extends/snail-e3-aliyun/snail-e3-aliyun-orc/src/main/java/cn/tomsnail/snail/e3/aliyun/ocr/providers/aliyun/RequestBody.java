package cn.tomsnail.snail.e3.aliyun.ocr.providers.aliyun;

import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.fastjson.JSON;

public class RequestBody {
	
	
	private String configure;
	
	private String image;

	public String getConfigure() {
		return configure;
	}

	public void setConfigure(String configure) {
		this.configure = configure;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public RequestBody(boolean face, String image) {
		if(face){
			this.configure = "{\"side\":\"face\"}";
		}else{
			this.configure = "{\"side\":\"back\"}";
		}
		this.image = image;
	}
	
	public byte[] body(){
		return JSON.toJSONString(this).getBytes(SdkConstant.CLOUDAPI_ENCODING);
	}
	
	

}

package cn.tomsnail.ocr.providers.aliyun;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.fastjson.JSON;

import cn.tomsnail.ocr.OrcContent;
import cn.tomsnail.ocr.OrcResult;
import cn.tomsnail.ocr.OrcType;
import cn.tomsnail.ocr.providers.BaseProvider;
import cn.tomsnail.ocr.providers.BaseProviderConfig;

public class AliyunProvider extends BaseProvider{

	

	public AliyunProvider() {
		super();
	}

	public AliyunProvider(BaseProviderConfig providerConfig) {
		super(providerConfig);
	}

	@Override
	public boolean ready() {
		return true;
	}

	@Override
	public OrcResult doPost(OrcContent orcContent) {
		
		if(providerConfig==null||orcContent==null){
			return OrcResult.NullError();
		}
		
		HttpApiClient.getInstance().init(providerConfig.getScheme(), providerConfig.getDomain(), providerConfig.getAppKey(), providerConfig.getAppSecret());
		ApiResponse apiResponse = HttpApiClient.getInstance().syncPost(providerConfig.getCallUrl(), new RequestBody(orcContent.face(),orcContent.getImgContent()).body());
		
		LOGGER.info("apiResponse is {}",apiResponse);
		
		OrcResult orcResult = new OrcResult();
		System.out.println(new String(apiResponse.getBody() , SdkConstant.CLOUDAPI_ENCODING));
		orcResult.setStatus(apiResponse.getCode());
		orcResult.setSuccess(apiResponse.getCode()==200);
		if(orcResult.isSuccess()){
			orcResult.setResult(JSON.parseObject(new String(apiResponse.getBody() , SdkConstant.CLOUDAPI_ENCODING), Map.class));
		}else{
			orcResult.setErrorDesc(StringUtils.join(apiResponse.getHeaders().get("X-Ca-Error-Message"),","));
		}
		
		return orcResult;
	}

	@Override
	public OrcResult doGet(OrcContent orcContent) {
		throw new RuntimeException("not support");
	}
	
	
	public static AliyunProvider build(BaseProviderConfig baseProviderConfig){
		return new AliyunProvider(baseProviderConfig);
	}
	
	public static void main(String[] args) {
		File file = new File("C:/Users/yangsong/Desktop/idtest.png");
		String file_data = "";
		
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[(int)(file.length())];
			fis.read(buf);
			file_data = Base64Utils.encodeToString(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		OrcContent orcContent = new OrcContent();
		orcContent.setImgContent(file_data);
		orcContent.setType(OrcType.IDCARD);
		
		BaseProviderConfig providerConfig = new BaseProviderConfig();
		
		providerConfig.setCallUrl("/rest/160601/ocr/ocr_idcard.json");
		providerConfig.setDomain("dm-51.data.aliyun.com");
		providerConfig.setScerType(1);
		providerConfig.setScheme("http");
		
		AliyunProvider aliyunProvider = new AliyunProvider();
		aliyunProvider.setProviderConfig(providerConfig);
		System.out.println(aliyunProvider.doOrc(orcContent).getResult().get("address"));
		System.out.println(aliyunProvider.doOrc(orcContent).getResult());
	}

}

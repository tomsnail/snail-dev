package cn.tomsnail.ocr.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.ocr.OrcContent;
import cn.tomsnail.ocr.OrcResult;
import cn.tomsnail.ocr.providers.aliyun.AliyunProvider;

public abstract class BaseProvider implements Provider{
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseProvider.class);
	
	protected BaseProviderConfig providerConfig;

	public BaseProviderConfig getProviderConfig() {
		return providerConfig;
	}

	public void setProviderConfig(BaseProviderConfig providerConfig) {
		this.providerConfig = providerConfig;
	}
	
	public abstract OrcResult doPost(OrcContent orcContent);
	
	public abstract OrcResult doGet(OrcContent orcContent);
	
	
	public OrcResult doOrc(OrcContent orcContent){
		return doPost(orcContent);
	}

	public BaseProvider(BaseProviderConfig providerConfig) {
		super();
		this.providerConfig = providerConfig;
	}

	public BaseProvider() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

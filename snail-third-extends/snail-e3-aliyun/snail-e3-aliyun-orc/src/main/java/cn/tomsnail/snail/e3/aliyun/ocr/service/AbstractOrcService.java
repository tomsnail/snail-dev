package cn.tomsnail.snail.e3.aliyun.ocr.service;

import java.util.Map;

import cn.tomsnail.snail.e3.aliyun.ocr.OrcContent;
import cn.tomsnail.snail.e3.aliyun.ocr.OrcResult;
import cn.tomsnail.snail.e3.aliyun.ocr.OrcType;
import cn.tomsnail.snail.e3.aliyun.ocr.providers.Provider;

public abstract class AbstractOrcService implements OrcService{

	protected Map<Integer,Provider> providerMap;
	
	public OrcResult orc(OrcContent orcContent){
		
		if(orcContent==null){
			return OrcResult.nullError();
		}
		
		if(providerMap==null||providerMap.isEmpty()){
			throw new NullPointerException("providerMap is null");
		}
		
		OrcType orcType = orcContent.getSubType()==null?orcContent.getType():orcContent.getSubType();
		
		if(orcType==null){
			throw new NullPointerException("orcType is null");
		}
		
		Provider provider = providerMap.get(orcType.getType());
		
		if(provider==null){
			provider = providerMap.get(orcType.getType()/10*10);
		}
		
		if(provider==null){
			throw new NullPointerException("provider is null");
		}
		
		return service(orcContent, provider);
	}
	
	

	public Map<Integer, Provider> getProviderConfigMap() {
		return providerMap;
	}

	public void setProviderConfigMap(Map<Integer, Provider> providergMap) {
		this.providerMap = providergMap;
	}
	
	protected OrcResult service(OrcContent orcContent,Provider provider) {
		
		if(orcContent==null||provider==null){
			return OrcResult.nullError();
		}
		
		if(provider.ready()){
			return provider.doOrc(orcContent);
		}
		
		return OrcResult.error();
	}
	
	
	protected abstract void initMap();
	
	

}

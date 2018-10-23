package cn.tomsnail.ocr.service.aliyun;

import java.util.HashMap;
import java.util.Map;

import cn.tomsnail.ocr.OrcType;
import cn.tomsnail.ocr.providers.BaseProviderConfig;
import cn.tomsnail.ocr.providers.Provider;
import cn.tomsnail.ocr.providers.aliyun.AliyunProvider;
import cn.tomsnail.ocr.service.AbstractOrcService;

public class AliyunOrcService extends AbstractOrcService{
	
	protected String appKey = "";
	
	protected String appSecert = "";
	
	
	public AliyunOrcService(String appKey,String appSecert){
		super();
		this.appKey = appKey;
		this.appSecert = appSecert;
	}
	
	protected void initMap(){
		Map<Integer,Provider> map = new HashMap<Integer, Provider>();
		map.put(OrcType.IDCARD.getType(), IDCARD());
		map.put(OrcType.BANKCARD.getType(), BANKCARD());
		map.put(OrcType.BUSINESS.getType(), BUSINESS());
		map.put(OrcType.DRIVING.getType(), DRIVING());
		map.put(OrcType.PASSPORT.getType(), PASSPORT());
		map.put(OrcType.RESIDENCE.getType(), RESIDENCE());
		map.put(OrcType.VEHICLE.getType(), VEHICLE());
		this.providerMap = map;
	}
	
	protected Provider IDCARD(){
		return new AliyunProvider(BaseProviderConfig.buildHttps("/rest/160601/ocr/ocr_idcard.json", "dm-51.data.aliyun.com", appKey, appSecert));
	}
	
	protected Provider DRIVING(){
		return new AliyunProvider(BaseProviderConfig.buildHttps("/rest/160601/ocr/ocr_driver_license.json", "dm-52.data.aliyun.com", appKey, appSecert));
	}
	
	protected Provider VEHICLE(){
		return new AliyunProvider(BaseProviderConfig.buildHttps("/rest/160601/ocr/ocr_vehicle.json", "dm-53.data.aliyun.com", appKey, appSecert));
	}
	
	protected Provider BUSINESS(){
		return new AliyunProvider(BaseProviderConfig.buildHttps("/rest/160601/ocr/ocr_business_license.json", "dm-58.data.aliyun.com", appKey, appSecert));
	}
	
	protected Provider BANKCARD(){
		return new AliyunProvider(BaseProviderConfig.buildHttps("/rest/160601/ocr/ocr_bank_card.json", "yhk.market.alicloudapi.com", appKey, appSecert));
	}
	
	protected Provider PASSPORT(){
		return new AliyunProvider(BaseProviderConfig.buildHttps("/rest/160601/ocr/ocr_passport.json", "ocrhz.market.alicloudapi.com", appKey, appSecert));
	}
	
	protected Provider RESIDENCE(){
		return new AliyunProvider(BaseProviderConfig.buildHttps("/api/predict/ocr_household_register", "household.market.alicloudapi.com", appKey, appSecert));
	}
	
}

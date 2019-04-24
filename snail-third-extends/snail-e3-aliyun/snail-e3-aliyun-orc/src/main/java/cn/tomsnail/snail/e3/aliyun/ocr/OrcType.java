package cn.tomsnail.snail.e3.aliyun.ocr;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public enum OrcType {

	IDCARD(10),IDCARD_FACE(11),IDCARD_BACK(12),DRIVING(20),VEHICLE(30),BUSINESS(40),BANKCARD(50),PASSPORT(60),RESIDENCE(70);
	
	private int type;

	OrcType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	public static OrcType get(int type){
		OrcType orcType = null;
		switch(type){
			case 10: orcType = IDCARD;break;
			case 11: orcType = IDCARD_FACE;break;
			case 12: orcType = IDCARD_BACK;break;
			case 20: orcType = DRIVING;break;
			case 30: orcType = VEHICLE;break;
			case 40: orcType = BUSINESS;break;
			case 50: orcType = BANKCARD;break;
			case 60: orcType = PASSPORT;break;
			case 70: orcType = RESIDENCE;break;
			default:orcType = IDCARD;break;
		}
		return orcType;
	}
	
	public static OrcType get(String type){
		
		if(StringUtils.isBlank(type)){
			return null;
		}
		
		if(NumberUtils.isCreatable(type)){
			return null;
		}
		
		
		OrcType orcType = null;
		switch(type){
			case "10": orcType = IDCARD;break;
			case "11": orcType = IDCARD_FACE;break;
			case "12": orcType = IDCARD_BACK;break;
			case "20": orcType = DRIVING;break;
			case "30": orcType = VEHICLE;break;
			case "40": orcType = BUSINESS;break;
			case "50": orcType = BANKCARD;break;
			case "60": orcType = PASSPORT;break;
			case "70": orcType = RESIDENCE;break;
			default:orcType = IDCARD;break;
		}
		return orcType;
	}
	
	
}

package cn.tomsnail.framwork.validator.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class BizValidator {
	
	
	private boolean isFastFail = false;
	
	
	private List<BizValidParam> bizValidParams = new ArrayList<BizValidParam>();
	
	
	public BizValidator add(Object obj,ValidHandler validHandler){
		bizValidParams.add(new BizValidParam(validHandler, obj));
		return this;
	}
	
	public BizValidator checkAll(){
		isFastFail = false;
		return this;
	}
	
	public BizValidator fastFail(){
		isFastFail = true;
		return this;
	}
	
	public ValidResult doValid(){
		
		boolean success = true;
		
		if(CollectionUtils.isNotEmpty(bizValidParams)){
			for(BizValidParam bizValidParam:bizValidParams){
				ValidResult validResult = null;
				try{
					validResult = bizValidParam.getValidHandler().validate(bizValidParam.getValue());
				}catch(Throwable e){
				}
				if(validResult==null){
					validResult = ValidResult.ERROR;
				}
				
				if(isFastFail&&!validResult.isSuccess()){
					return validResult;
				}
				if(success&&!validResult.isSuccess()){
					success = false;
				}
				bizValidParam.setValidResult(validResult);
			}
			
			
			if(!success){
				StringBuffer errorDesc = new StringBuffer();
				for(BizValidParam bizValidParam:bizValidParams){
					if(!bizValidParam.getValidResult().isSuccess()){
						errorDesc.append("[")
								 .append(bizValidParam.getValue())
								 .append(":")
								 .append(bizValidParam.getValidResult().getErrorDesc())
								 .append("]")
								 .append(" ");
					}
				}
				return ValidResult.error(errorDesc.toString());
			}
		}
		
		return ValidResult.OK;
	}
	

}

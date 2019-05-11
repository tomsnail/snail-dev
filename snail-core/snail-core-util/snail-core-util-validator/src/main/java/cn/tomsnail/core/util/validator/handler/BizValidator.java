package cn.tomsnail.core.util.validator.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class BizValidator {
	
	
	private boolean isFastFail = false;
	
	
	private List<BizValidParam> bizValidParams = new ArrayList<BizValidParam>();
	
	private List<FValidHandler> validHandlers = new ArrayList<FValidHandler>();
	
	
	public BizValidator add(Object obj,ValidHandler validHandler){
		bizValidParams.add(new BizValidParam(validHandler, obj));
		return this;
	}
	
	public BizValidator add(FValidHandler validHandler){
		validHandlers.add(validHandler);
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
		StringBuffer errorDesc = new StringBuffer();
		if(CollectionUtils.isNotEmpty(validHandlers)){
			
			for(FValidHandler validHandler:validHandlers){
				ValidResult validResult = null;
				try{
					validResult = validHandler.validate();
				}catch(Throwable e){
				}
				if(validResult==null){
					validResult = ValidResult.ERROR;
				}
				
				if(!validResult.isSuccess()){
					errorDesc.append("[")
					 .append("")
					 .append(":")
					 .append(validResult.getErrorDesc())
					 .append("]")
					 .append(" ");
				}
				if(isFastFail&&!validResult.isSuccess()){
					return validResult;
				}
			}
		}
		
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

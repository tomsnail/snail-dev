#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import java.util.List;


import cn.tomsnail.snail.core.page.OrderBy;
import cn.tomsnail.snail.core.util.commons.CollectionUtils;
import cn.tomsnail.snail.core.util.string.StringUtils;

import ${package}.api.DispatchUrlMo;


/**
 * DispatchUrl Convert
 * @author Yangsong
 * @version 0.0.1-SNAPSHOT
 */
public class DispatchUrlConvert   {
	
	
	public static DispatchUrlMo convert(DispatchUrlDto dispatchUrlDto){
		
		if(dispatchUrlDto==null){
			return null;
		}
		
		 DispatchUrlMo  dispatchUrlMo = new  DispatchUrlMo();
		 
		 dispatchUrlMo.setId(dispatchUrlDto.getId());				
		 dispatchUrlMo.setStrategyId(dispatchUrlDto.getStrategyId());				
		 dispatchUrlMo.setUrlType(dispatchUrlDto.getUrlType());				
		 dispatchUrlMo.setUrlContext(dispatchUrlDto.getUrlContext());				
		 dispatchUrlMo.setUrlUserName(dispatchUrlDto.getUrlUserName());				
		 dispatchUrlMo.setUrlPassword(dispatchUrlDto.getUrlPassword());				
		 dispatchUrlMo.setUrlAdditional(dispatchUrlDto.getUrlAdditional());				
		 dispatchUrlMo.setCreateDate(dispatchUrlDto.getCreateDate());				
		 dispatchUrlMo.setCreateBy(dispatchUrlDto.getCreateBy());				
		 dispatchUrlMo.setUpdateDate(dispatchUrlDto.getUpdateDate());				
		 dispatchUrlMo.setUpdateBy(dispatchUrlDto.getUpdateBy());				
		 dispatchUrlMo.setDelFlag(dispatchUrlDto.getDelFlag());				
		 dispatchUrlMo.setRemarks(dispatchUrlDto.getRemarks());				
		 
		 return dispatchUrlMo;
	}
	
	
	public static DispatchUrlDto convert(DispatchUrlMo dispatchUrlMo){
		
		if(dispatchUrlMo==null){
			return null;
		}
		
		 DispatchUrlDto  dispatchUrlDto = new  DispatchUrlDto();
		 
		 dispatchUrlDto.setId(dispatchUrlMo.getId());				
		 dispatchUrlDto.setStrategyId(dispatchUrlMo.getStrategyId());				
		 dispatchUrlDto.setUrlType(dispatchUrlMo.getUrlType());				
		 dispatchUrlDto.setUrlContext(dispatchUrlMo.getUrlContext());				
		 dispatchUrlDto.setUrlUserName(dispatchUrlMo.getUrlUserName());				
		 dispatchUrlDto.setUrlPassword(dispatchUrlMo.getUrlPassword());				
		 dispatchUrlDto.setUrlAdditional(dispatchUrlMo.getUrlAdditional());				
		 dispatchUrlDto.setCreateDate(dispatchUrlMo.getCreateDate());				
		 dispatchUrlDto.setCreateBy(dispatchUrlMo.getCreateBy());				
		 dispatchUrlDto.setUpdateDate(dispatchUrlMo.getUpdateDate());				
		 dispatchUrlDto.setUpdateBy(dispatchUrlMo.getUpdateBy());				
		 dispatchUrlDto.setDelFlag(dispatchUrlMo.getDelFlag());				
		 dispatchUrlDto.setRemarks(dispatchUrlMo.getRemarks());				
		 
		 return dispatchUrlDto;
	}
	
	
	public static String getDtoFiledName(String moFiledName){
		
		if(StringUtils.isBlank(moFiledName)){
			return "id";
		}
		
		
		if("id".equals(moFiledName)){
			return "Id";
		}
		if("strategyId".equals(moFiledName)){
			return "StrategyId";
		}
		if("urlType".equals(moFiledName)){
			return "UrlType";
		}
		if("urlContext".equals(moFiledName)){
			return "UrlContext";
		}
		if("urlUserName".equals(moFiledName)){
			return "UrlUserName";
		}
		if("urlPassword".equals(moFiledName)){
			return "UrlPassword";
		}
		if("urlAdditional".equals(moFiledName)){
			return "UrlAdditional";
		}
		if("createDate".equals(moFiledName)){
			return "CreateDate";
		}
		if("createBy".equals(moFiledName)){
			return "CreateBy";
		}
		if("updateDate".equals(moFiledName)){
			return "UpdateDate";
		}
		if("updateBy".equals(moFiledName)){
			return "UpdateBy";
		}
		if("delFlag".equals(moFiledName)){
			return "DelFlag";
		}
		if("remarks".equals(moFiledName)){
			return "Remarks";
		}
		
		
		
		return "id";
	}
	
	
	public static String orderByStr(List<OrderBy> orderBys){
		
		if(CollectionUtils.isEmpty(orderBys)){
			return null;
		}
		
		StringBuffer stringBuffer = new StringBuffer();
		
		for(OrderBy orderBy:orderBys){
			stringBuffer.append(" ").append(getDtoFiledName(orderBy.getOrderName())).append(" ").append(orderBy.getOrderType()).append(",");
		}
		stringBuffer.append(" ").append("id").append(" ");
		
		return stringBuffer.toString();
	}
	
}
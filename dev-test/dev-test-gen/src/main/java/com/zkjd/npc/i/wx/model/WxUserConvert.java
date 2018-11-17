/**
 * Copyright &copy; 2012-2016
 */
package com.zkjd.npc.i.wx.model;

import java.util.List;


import cn.tomsnail.framwork.core.page.OrderBy;
import cn.tomsnail.util.commons.CollectionUtils;
import cn.tomsnail.util.string.StringUtils;


/**
 *  Convert
 * @author Wangpeng
 * @version 2018-11-15
 */
public class WxUserConvert   {
	
	
	public static WxUserMo convert(WxUserDto wxUserDto){
		
		if(wxUserDto==null){
			return null;
		}
		
		 WxUserMo  wxUserMo = new  WxUserMo();
		 
		 wxUserMo.setId(wxUserDto.getId());				
		 wxUserMo.setUid(wxUserDto.getUid());				
		 wxUserMo.setOpenId(wxUserDto.getOpenId());				
		 wxUserMo.setUserId(wxUserDto.getUserId());				
		 wxUserMo.setCompanyId(wxUserDto.getCompanyId());				
		 wxUserMo.setCompanyName(wxUserDto.getCompanyName());				
		 wxUserMo.setBingStatus(wxUserDto.getBingStatus());				
		 wxUserMo.setCreateBy(wxUserDto.getCreateBy());				
		 wxUserMo.setCreateDate(wxUserDto.getCreateDate());				
		 wxUserMo.setUpdateBy(wxUserDto.getUpdateBy());				
		 wxUserMo.setUpdateDate(wxUserDto.getUpdateDate());				
		 wxUserMo.setRemarks(wxUserDto.getRemarks());				
		 wxUserMo.setVersion(wxUserDto.getVersion());				
		 wxUserMo.setDelFlag(wxUserDto.getDelFlag());				
		 
		 return wxUserMo;
	}
	
	
	public static WxUserDto convert(WxUserMo wxUserMo){
		
		if(wxUserMo==null){
			return null;
		}
		
		 WxUserDto  wxUserDto = new  WxUserDto();
		 
		 wxUserDto.setId(wxUserMo.getId());				
		 wxUserDto.setUid(wxUserMo.getUid());				
		 wxUserDto.setOpenId(wxUserMo.getOpenId());				
		 wxUserDto.setUserId(wxUserMo.getUserId());				
		 wxUserDto.setCompanyId(wxUserMo.getCompanyId());				
		 wxUserDto.setCompanyName(wxUserMo.getCompanyName());				
		 wxUserDto.setBingStatus(wxUserMo.getBingStatus());				
		 wxUserDto.setCreateBy(wxUserMo.getCreateBy());				
		 wxUserDto.setCreateDate(wxUserMo.getCreateDate());				
		 wxUserDto.setUpdateBy(wxUserMo.getUpdateBy());				
		 wxUserDto.setUpdateDate(wxUserMo.getUpdateDate());				
		 wxUserDto.setRemarks(wxUserMo.getRemarks());				
		 wxUserDto.setVersion(wxUserMo.getVersion());				
		 wxUserDto.setDelFlag(wxUserMo.getDelFlag());				
		 
		 return wxUserDto;
	}
	
	
	public static String getDtoFiledName(String moFiledName){
		
		if(StringUtils.isBlank(moFiledName)){
			return "id";
		}
		
		
		if("id".equals(moFiledName)){
			return "Id";
		}
		if("uid".equals(moFiledName)){
			return "Uid";
		}
		if("openId".equals(moFiledName)){
			return "OpenId";
		}
		if("userId".equals(moFiledName)){
			return "UserId";
		}
		if("companyId".equals(moFiledName)){
			return "CompanyId";
		}
		if("companyName".equals(moFiledName)){
			return "CompanyName";
		}
		if("bingStatus".equals(moFiledName)){
			return "BingStatus";
		}
		if("createBy".equals(moFiledName)){
			return "CreateBy";
		}
		if("createDate".equals(moFiledName)){
			return "CreateDate";
		}
		if("updateBy".equals(moFiledName)){
			return "UpdateBy";
		}
		if("updateDate".equals(moFiledName)){
			return "UpdateDate";
		}
		if("remarks".equals(moFiledName)){
			return "Remarks";
		}
		if("version".equals(moFiledName)){
			return "Version";
		}
		if("delFlag".equals(moFiledName)){
			return "DelFlag";
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
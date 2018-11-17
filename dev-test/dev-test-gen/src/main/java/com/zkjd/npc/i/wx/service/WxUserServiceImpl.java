package com.zkjd.npc.i.wx.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tomsnail.framwork.core.page.Page;
import com.zkjd.npc.i.wx.dao.WxUserDao;
import com.zkjd.npc.i.wx.model.WxUserConvert;
import com.zkjd.npc.i.wx.model.WxUserDto;
import com.zkjd.npc.i.wx.model.WxUserMo;
import cn.tomsnail.util.string.StringUtils;
import cn.tomsnail.uuid.UuidUtil;

@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService{
	
	@Autowired
	private WxUserDao wxUserDao;

	@Override
	public List<WxUserMo> findList(WxUserMo wxUser) {
		
		
		if(wxUser==null){
			return null;
		}
		
		
		List<WxUserMo> wxUserMos = new ArrayList<WxUserMo>();
		
		List<WxUserDto> wxUserDtos = wxUserDao.findList(WxUserConvert.convert(wxUser));
		
		for(WxUserDto wxUserDto:wxUserDtos){
			wxUserMos.add(WxUserConvert.convert(wxUserDto));
		}
		
		return wxUserMos;
	}

	@Override
	public WxUserMo get(String id) {
		
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		WxUserDto wxUserDto = wxUserDao.get(id);
		
		if(wxUserDto==null){
			return null;
		}
		
		return WxUserConvert.convert(wxUserDto);
	}

	@Override
	public int findPageCount(WxUserMo wxUser) {
		
		if(wxUser==null){
			return 0;
		}
		
		return wxUserDao.findPageCount(WxUserConvert.convert(wxUser));
	}
	
	
	@Override
	public List<WxUserMo> findPage(WxUserMo wxUser,Page page) {
		
		if(wxUser==null){
			return null;
		}
		
		if(page==null){
			return this.findList(wxUser);
		}
		
		WxUserDto wxUserDto = WxUserConvert.convert(wxUser);
		wxUserDto.setOrderBy(WxUserConvert.orderByStr(page.getOrderBys()));

		
		wxUserDto.setPage(new cn.tomsnail.dao.plugins.pagination.Page(page.getPageNo(), page.getPageSize()));
		
		List<WxUserMo> wxUserMos = new ArrayList<WxUserMo>();
		
		List<WxUserDto> wxUserDtos = wxUserDao.findList(wxUserDto);
		
		for(WxUserDto _wxUserDto:wxUserDtos){
			wxUserMos.add(WxUserConvert.convert(_wxUserDto));
		}
		
		return wxUserMos;
	}
	
	

	@Override
	public boolean save(WxUserMo wxUser) {
		
		if(wxUser==null){
			return false;
		}
		
		if(StringUtils.isBlank(wxUser.getId())){
			wxUser.setId(UuidUtil.getNUUID());
		}
		
		return wxUserDao.insert(WxUserConvert.convert(wxUser))==1;
	}

	@Override
	public boolean saveBatch(List<WxUserMo> wxUsers) {
		return false;
	}

	@Override
	public boolean delete(String id) {
		
		if(StringUtils.isBlank(id)){
			return false;
		}
		
		return wxUserDao.delete(id)==1;
	}

	@Override
	public boolean deleteBatch(List<String> ids) {
		return false;
	}

	@Override
	public boolean update(WxUserMo wxUser) {
		
		if(wxUser==null){
			return false;
		}
		
		return wxUserDao.update(WxUserConvert.convert(wxUser))==1;
	}

	@Override
	public boolean updateBatch(List<WxUserMo> wxUsers) {
		return false;
	}



}
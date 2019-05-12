package ${package}.service;


import ${package}.api.DispatchUrlMo;
import ${package}.dao.DispatchUrlDao;
import ${package}.model.DispatchUrlConvert;
import ${package}.model.DispatchUrlDto;
import cn.tomsnail.snail.core.page.Page;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.core.util.uuid.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dispatchUrlBaseService")
public class DispatchUrlBaseServiceImpl implements DispatchUrlBaseService{
	
	@Autowired
	private DispatchUrlDao dispatchUrlDao;

	@Override
	public List<DispatchUrlMo> findList(DispatchUrlMo dispatchUrl) {
		
		
		if(dispatchUrl==null){
			return null;
		}
		
		
		List<DispatchUrlMo> dispatchUrlMos = new ArrayList<DispatchUrlMo>();
		
		List<DispatchUrlDto> dispatchUrlDtos = dispatchUrlDao.findList(DispatchUrlConvert.convert(dispatchUrl));
		
		for(DispatchUrlDto dispatchUrlDto:dispatchUrlDtos){
			dispatchUrlMos.add(DispatchUrlConvert.convert(dispatchUrlDto));
		}
		
		return dispatchUrlMos;
	}

	@Override
	public DispatchUrlMo get(String id) {
		
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		DispatchUrlDto dispatchUrlDto = dispatchUrlDao.get(id);
		
		if(dispatchUrlDto==null){
			return null;
		}
		
		return DispatchUrlConvert.convert(dispatchUrlDto);
	}

	@Override
	public int findPageCount(DispatchUrlMo dispatchUrl) {
		
		if(dispatchUrl==null){
			return 0;
		}
		
		return dispatchUrlDao.findPageCount(DispatchUrlConvert.convert(dispatchUrl));
	}
	
	
	@Override
	public List<DispatchUrlMo> findPage(DispatchUrlMo dispatchUrl,Page page) {
		
		if(dispatchUrl==null){
			return null;
		}
		
		if(page==null){
			return this.findList(dispatchUrl);
		}
		
		DispatchUrlDto dispatchUrlDto = DispatchUrlConvert.convert(dispatchUrl);
		dispatchUrlDto.setOrderBy(DispatchUrlConvert.orderByStr(page.getOrderBys()));

		
		dispatchUrlDto.setPage(new cn.tomsnail.snail.core.ds.dao.plugins.pagination.Page(page.getPageNo(), page.getPageSize()));
		
		List<DispatchUrlMo> dispatchUrlMos = new ArrayList<DispatchUrlMo>();
		
		List<DispatchUrlDto> dispatchUrlDtos = dispatchUrlDao.findList(dispatchUrlDto);
		
		for(DispatchUrlDto _dispatchUrlDto:dispatchUrlDtos){
			dispatchUrlMos.add(DispatchUrlConvert.convert(_dispatchUrlDto));
		}
		
		return dispatchUrlMos;
	}
	
	

	@Override
	public boolean save(DispatchUrlMo dispatchUrl) {
		
		if(dispatchUrl==null){
			return false;
		}
		
		if(StringUtils.isBlank(dispatchUrl.getId())){
			dispatchUrl.setId(UuidUtil.getNUUID());
		}
		
		return dispatchUrlDao.insert(DispatchUrlConvert.convert(dispatchUrl))==1;
	}

	@Override
	public boolean saveBatch(List<DispatchUrlMo> dispatchUrls) {
		return false;
	}

	@Override
	public boolean delete(String id) {
		
		if(StringUtils.isBlank(id)){
			return false;
		}
		
		return dispatchUrlDao.delete(id)==1;
	}

	@Override
	public boolean deleteBatch(List<String> ids) {
		return false;
	}

	@Override
	public boolean update(DispatchUrlMo dispatchUrl) {
		
		if(dispatchUrl==null){
			return false;
		}
		
		return dispatchUrlDao.update(DispatchUrlConvert.convert(dispatchUrl))==1;
	}

	@Override
	public boolean updateBatch(List<DispatchUrlMo> dispatchUrls) {
		return false;
	}



}
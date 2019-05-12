package cn.tomsnail.snail.example.gen.ex.dubbo;


import cn.tomsnail.snail.core.page.Page;
import cn.tomsnail.snail.example.gen.ex.api.DispatchUrlMo;
import cn.tomsnail.snail.example.gen.ex.api.DispatchUrlService;
import cn.tomsnail.snail.example.gen.ex.service.DispatchUrlBaseService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DispatchUrlServiceDubbo implements DispatchUrlService {

	@Autowired
	private DispatchUrlBaseService dispatchUrlBaseService;

	@Override
	public List<DispatchUrlMo> findList(DispatchUrlMo dispatchUrl) {
		
		

		return dispatchUrlBaseService.findList(dispatchUrl);
	}

	@Override
	public DispatchUrlMo get(String id) {
		
		return dispatchUrlBaseService.get(id);
	}

	@Override
	public int findPageCount(DispatchUrlMo dispatchUrl) {
		
		return dispatchUrlBaseService.findPageCount(dispatchUrl);
	}
	
	
	@Override
	public List<DispatchUrlMo> findPage(DispatchUrlMo dispatchUrl,Page page) {
		
		return dispatchUrlBaseService.findPage(dispatchUrl,page);
	}
	
	

	@Override
	public boolean save(DispatchUrlMo dispatchUrl) {
		
		return dispatchUrlBaseService.save(dispatchUrl);
	}

	@Override
	public boolean saveBatch(List<DispatchUrlMo> dispatchUrls) {
		return false;
	}

	@Override
	public boolean delete(String id) {
		
		return dispatchUrlBaseService.delete(id);
	}

	@Override
	public boolean deleteBatch(List<String> ids) {
		return false;
	}

	@Override
	public boolean update(DispatchUrlMo dispatchUrl) {
		
		return dispatchUrlBaseService.update(dispatchUrl);
	}

	@Override
	public boolean updateBatch(List<DispatchUrlMo> dispatchUrls) {
		return false;
	}



}
#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dubbo;


import cn.tomsnail.snail.core.page.Page;
import com.alibaba.dubbo.config.annotation.Service;
import ${package}.api.DispatchUrlMo;
import ${package}.api.DispatchUrlService;
import ${package}.service.DispatchUrlBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DispatchUrlServiceDubbo implements DispatchUrlService{

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
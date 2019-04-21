package cn.tomsnail.snail.core.dubbo.restful.filter;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年9月27日 下午1:54:44
	* @see 
	*/     
@Component
public class RestfulFilterManager {

	@Autowired(required=false)
	private List<RestfulFilter> filters;

	public List<RestfulFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<RestfulFilter> filters) {
		this.filters = filters;
	}
}

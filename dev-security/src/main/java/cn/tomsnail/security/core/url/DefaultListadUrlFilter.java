package cn.tomsnail.security.core.url;

import java.util.List;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月9日 下午3:23:14
 * @see 
 */
public class DefaultListadUrlFilter implements IUrlFilter{
	
	private List<IUrlFilter> urlFilters;

	@Override
	public boolean isPass(String url) {
		if(url==null) return false;
		
		for(IUrlFilter urlFilter:urlFilters){
			if(urlFilter.isPass(url)){
				return true;
			}
		}
		return false;
	}

	public List<IUrlFilter> getUrlFilters() {
		return urlFilters;
	}

	public void setUrlFilters(List<IUrlFilter> urlFilters) {
		this.urlFilters = urlFilters;
	}
	
	

}

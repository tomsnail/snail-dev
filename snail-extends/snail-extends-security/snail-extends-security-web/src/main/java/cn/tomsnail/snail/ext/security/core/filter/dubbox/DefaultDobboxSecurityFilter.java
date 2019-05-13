package cn.tomsnail.snail.ext.security.core.filter.dubbox;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import cn.tomsnail.snail.ext.security.core.filter.IFilter;




/**
 *        默认dubbox安全过滤
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午6:05:08
 * @see 
 */
public class DefaultDobboxSecurityFilter implements ContainerRequestFilter {
	
	private IFilter filter;

	public IFilter getFilter() {
		return filter;
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		if(filter!=null){
			if(filter.doFilter(requestContext)){
			}else{
				requestContext.abortWith(Response.status(401).build());
			}
		}
	}


	
}

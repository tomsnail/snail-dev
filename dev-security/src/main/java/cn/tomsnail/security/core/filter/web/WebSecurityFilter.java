package cn.tomsnail.security.core.filter.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

import cn.tomsnail.framwork.http.CommonMessage;
import cn.tomsnail.framwork.http.ResultData;
import cn.tomsnail.security.core.filter.IFilter;

/**
 *        servlet filter 安全过滤
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午5:37:39
 * @see 
 */
public class WebSecurityFilter implements Filter{

	private IFilter filter;
	
	private boolean isFilter = true;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(!isFilter){
			chain.doFilter(request, response);
		}else{
			BodyReaderHttpServletRequestWrapper requestWrapper = null;   
			requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
			String body = requestWrapper.getBodyStr();
			boolean isSucess = false;
			isSucess = filter.doFilter(body);
			if(isSucess){
				chain.doFilter(requestWrapper, response);
			}else{
				ResultData<Object> result = new ResultData<Object>();
				result.setStatus(CommonMessage.UNAUTHORIZED);
				result.setMsg("请重新登录");
				try {
					result.setBody("");
					response.getWriter().print(objectMapper.writeValueAsString(result));
					response.getWriter().flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void destroy() {
		
	}


	public IFilter getFilter() {
		return filter;
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	public boolean isFilter() {
		return isFilter;
	}

	public void setFilter(boolean isFilter) {
		this.isFilter = isFilter;
	}
	
	

}

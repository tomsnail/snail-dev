package cn.tomsnail.snail.ext.security.core.filter;

import java.util.Map;

import cn.tomsnail.snail.ext.security.core.authentication.AuthentToken;
import cn.tomsnail.snail.ext.security.core.authentication.IAuthenticationService;
import cn.tomsnail.snail.ext.security.core.authorization.IAuthorizationService;
import cn.tomsnail.snail.ext.security.core.authorization.model.TResource;
import cn.tomsnail.snail.ext.security.core.authorization.model.TUser;
import cn.tomsnail.snail.ext.security.core.url.IUrlFilter;



/**
 *        默认的基础安全过滤器
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午6:30:30
 * @see 
 */
public class DefaultSecurityBasicFilter implements IFilter{

	/**
	 * 验证服务
	 */
	private IAuthenticationService authenticationService;
	
	/**
	 * 权限服务
	 */
	private IAuthorizationService authorizationService;
	
	/**
	 * 参数适配
	 */
	private IParamAdapter paramAdapter;
	
	/**
	 * url过滤
	 */
	private IUrlFilter urlFilter;
	
	/**
	 * 扩展过滤
	 */
	private ExtendFilter beforeFilter;
	
	/**
	 * 扩展过滤
	 */
	private ExtendFilter afterFilter;

	@Override
	public boolean doFilter(Object obj) {
		if(beforeFilter!=null){
			if(!beforeFilter.doFilter(obj)) return false;
		}
		boolean isSucess = false;
		Map<String,Object> paramMap = paramAdapter.getParamMap(obj);
		if(paramMap==null){
			return isSucess;
		}
		if(urlFilter!=null){
			if(!paramMap.containsKey("URL")){
				throw new SecurityException("ParamAdapte must contains URL key");
			}else{
				String url = paramMap.remove("URL")+"";
				if(urlFilter.isPass(url)){
					return true;
				}
			}
		}
		paramMap.remove("URL");
		AuthentToken token = paramAdapter.getToken(paramMap);
		if(authenticationService.validateToken(token)){
			TResource resource = paramAdapter.getTResource(paramMap);
			TUser user = paramAdapter.getTUser(paramMap);
			if(authorizationService.authorization(resource, user)){
				isSucess = true;
			}
		}
		if(afterFilter!=null){
			if(!afterFilter.doFilter(obj)){
				return false;
			}
		}
		return isSucess;
	}

	public IAuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(
			IAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public IAuthorizationService getAuthorizationService() {
		return authorizationService;
	}

	public void setAuthorizationService(IAuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	public IParamAdapter getParamAdapter() {
		return paramAdapter;
	}

	public void setParamAdapter(IParamAdapter paramAdapter) {
		this.paramAdapter = paramAdapter;
	}

	public IUrlFilter getUrlFilter() {
		return urlFilter;
	}

	public void setUrlFilter(IUrlFilter urlFilter) {
		this.urlFilter = urlFilter;
	}

	public ExtendFilter getBeforeFilter() {
		return beforeFilter;
	}

	public void setBeforeFilter(ExtendFilter beforeFilter) {
		this.beforeFilter = beforeFilter;
	}

	public ExtendFilter getAfterFilter() {
		return afterFilter;
	}

	public void setAfterFilter(ExtendFilter afterFilter) {
		this.afterFilter = afterFilter;
	}

}

package cn.tomsnail.snail.ext.security.core.authorization;

import java.util.List;

import cn.tomsnail.snail.ext.security.core.authorization.model.TResource;
import cn.tomsnail.snail.ext.security.core.authorization.model.TUser;




/**
 *        默认鉴权服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午4:57:25
 * @see 
 */
public class DefaultAuthorizationService implements IAuthorizationService {

	private List<IAuthorizationService> authorizationServices;
	
	@Override
	public boolean authorization(TResource resource,TUser user) {
		if(authorizationServices==null||authorizationServices.size()<1)return true;
		for(IAuthorizationService authorizationService:authorizationServices){
			if(authorizationService.authorization(resource,user)){
				return true;
			}
		}
		return false;
	}

	public List<IAuthorizationService> getAuthorizationServices() {
		return authorizationServices;
	}

	public void setAuthorizationServices(
			List<IAuthorizationService> authorizationServices) {
		this.authorizationServices = authorizationServices;
	}
	
	

}

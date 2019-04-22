package cn.tomsnail.snail.ext.security.core.authorization;

import cn.tomsnail.snail.ext.security.core.authorization.model.TResource;
import cn.tomsnail.snail.ext.security.core.authorization.model.TRole;
import cn.tomsnail.snail.ext.security.core.authorization.model.TUser;

/**
 *        默认数据库鉴权服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午5:10:31
 * @see 
 */
public class DefaultDatabaseAuthService implements IAuthorizationService{

	private IAuthDao authDao;
	
	private ICacheDao cacheDao;
	
	@Override
	public boolean authorization(TResource resource, TUser user) {
		TRole role = null;
		if(cacheDao!=null){
			role = cacheDao.getRoleByUser(user);
		}
		if(role==null){
			role = authDao.getRoleByUser(user);
			if(role!=null&&cacheDao!=null){
				cacheDao.saveUserRole(user, role);
			}
		}
		if(role==null){
			return false;
		}else{
			if(role.isContains(resource)){
				return true;
			}
		}
		return false;
	}

	public IAuthDao getAuthDao() {
		return authDao;
	}

	public void setAuthDao(IAuthDao authDao) {
		this.authDao = authDao;
	}

	public ICacheDao getCacheDao() {
		return cacheDao;
	}

	public void setCacheDao(ICacheDao cacheDao) {
		this.cacheDao = cacheDao;
	}
	
}

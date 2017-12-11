package cn.tomsnail.security.core.authorization;

import cn.tomsnail.security.core.authorization.model.TResource;
import cn.tomsnail.security.core.authorization.model.TUser;

/**
 *        鉴权服务接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午4:55:22
 * @see 
 */
public interface IAuthorizationService {

	/**
	 *        鉴权
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:26:33
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean authorization(TResource resource,TUser user);
	
}

package cn.tomsnail.snail.ext.security.core.authorization;

import cn.tomsnail.snail.ext.security.core.authorization.model.TRole;
import cn.tomsnail.snail.ext.security.core.authorization.model.TUser;

/**
 *        权限dao
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午5:14:24
 * @see 
 */
public interface IAuthDao {

	/**
	 *        获取用户角色
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:25:36
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public TRole getRoleByUser(TUser user);
	
	/**
	 *        保存用户角色
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:25:50
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void saveUserRole(TUser user,TRole role);
	
}

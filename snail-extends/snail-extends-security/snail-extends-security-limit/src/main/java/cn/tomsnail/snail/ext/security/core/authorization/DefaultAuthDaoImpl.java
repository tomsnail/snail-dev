package cn.tomsnail.snail.ext.security.core.authorization;

import org.springframework.jdbc.core.JdbcTemplate;

import cn.tomsnail.snail.ext.security.core.authorization.model.TRole;
import cn.tomsnail.snail.ext.security.core.authorization.model.TUser;



/**
 *        默认权限数据操作
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午5:15:28
 * @see 
 */
public class DefaultAuthDaoImpl implements IAuthDao{

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public TRole getRoleByUser(TUser user) {
		return new TRole();
	}

	@Override
	public void saveUserRole(TUser user, TRole role) {
		//not implement
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

}

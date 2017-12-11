package cn.tomsnail.security.core.authentication;

import java.util.Map;

/**
 *     验证服务接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午1:50:15
 * @see 
 */
public interface IAuthenticationService {

	/**
	 *        创建token
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:03:26
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public AuthentToken createToken(String key,Map<String,Object> addtionMap);
	
	/**
	 *        验证token
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:03:34
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean validateToken(AuthentToken token);
	
}

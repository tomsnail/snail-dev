package cn.tomsnail.snail.ext.security.core.filter;


import java.util.Map;

import cn.tomsnail.snail.ext.security.core.authentication.AuthentToken;
import cn.tomsnail.snail.ext.security.core.authorization.model.TResource;
import cn.tomsnail.snail.ext.security.core.authorization.model.TUser;



/**
 *        参数适配
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午5:46:13
 * @see 
 */
public interface IParamAdapter {

	/**
	 *  获取token      
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:28:29
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public AuthentToken getToken(Map<String,Object> paramMap);
	
	/**
	 *        获取资源
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:28:37
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public TResource getTResource(Map<String,Object> paramMap);
	
	/**
	 *        获取用户
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:28:44
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public TUser getTUser(Map<String,Object> paramMap);
	
	/**
	 *        获得参数映射
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:28:53
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public Map<String,Object> getParamMap(Object obj);
	
}

package cn.tomsnail.security.core.authentication;

import java.util.Map;

/**
 *        key创建者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午2:02:28
 * @see 
 */
public interface IKeyCreator {

	/**
	 *        获取key
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:58:54
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public Key getKey(String key,Map<String,Object> addtionMap);
	
	/**
	 *        验证key
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:59:06
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean validtor(Key key,AuthentToken token);
	
}

package cn.tomsnail.snail.ext.security.core.authentication;

/**
 *        key存储接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午1:59:48
 * @see 
 */
public interface IKeyStore {

	/**
	 *        保存
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:03:50
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void save(Key key);
	
	/**
	 *        查询
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午6:03:55
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public Key get(String key);
	
}

package cn.tomsnail.util.security;

/**
 *        安全验证器
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年4月5日 下午12:23:44
 * @see 
 */
public interface IValidator {

	/**
	 *        验证
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年4月5日 下午12:23:54
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean validate(Object obj);
	
	/**
	 *        消除
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年4月5日 下午12:24:01
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String clean(String str);
	
}

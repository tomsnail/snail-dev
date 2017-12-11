package cn.tomsnail.http.register.core;

/**
 *        注册器工厂接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:42:56
 * @see 
 */
public interface IRegisterFactory {

	/**
	 *        获取注册器
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:43:08
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public IRegister getRegister(String url);
	
}

package cn.tomsnail.http.client.register;

/**
 *        注册工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月29日 下午2:14:17
 * @see 
 */
public interface IRegisterFactory {

	/**
	 *        依据地址获得注册器
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年8月29日 下午2:14:21
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public IRegister getRegister(String address);
	
}

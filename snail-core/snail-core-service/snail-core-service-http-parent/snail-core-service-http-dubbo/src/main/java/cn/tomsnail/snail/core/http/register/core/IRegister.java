package cn.tomsnail.snail.core.http.register.core;

import cn.tomsnail.snai.core.service.http.core.RegisterObject;

/**
 *        服务端注册接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 上午10:23:18
 * @see 
 */
public interface IRegister {

	/**
	 *        注册服务端
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:42:24
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void register(RegisterObject registerObject);
}

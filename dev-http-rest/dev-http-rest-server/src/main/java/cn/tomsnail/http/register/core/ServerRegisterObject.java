package cn.tomsnail.http.register.core;

import cn.tomsnail.http.core.RegisterObject;

/**
 *        服务端注册类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月5日 下午2:14:33
 * @see 
 */
public class ServerRegisterObject extends RegisterObject{
	

	/**
	 *        注册信息，服务端需要地址和名称
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:41:35
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception
	 */
	@Override
	public String geRegisterInfo(){
		return this.getAddUrl()+":M:"+this.getAppName();
	}
	
	/**
	 *        组注册信息
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:41:40
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String geGroupRegisterInfo(){
		return "G:"+this.getGroup();
	}
	
	/**
	 *        获取注册url
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:41:43
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static String getRegisterUrl(String registerUrl){
		return registerUrl.split(":M:")[0];
	}
}

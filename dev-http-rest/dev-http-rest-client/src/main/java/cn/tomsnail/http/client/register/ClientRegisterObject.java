package cn.tomsnail.http.client.register;




import cn.tomsnail.http.core.RegisterObject;

/**
 *        客户端注册对象
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月5日 下午2:12:21
 * @see 
 */
public class ClientRegisterObject extends RegisterObject{

	
	protected String address;
	
	protected String registerType;
	
	
	
	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
	
	



	public String getRegisterType() {
		return registerType;
	}



	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}



	/**
	 *        客户端注册信息，只需要地址和模块名称
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:28:40
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception
	 */
	@Override
	public String geRegisterInfo() {
		return "//C//"+this.getAddUrl()+":M:"+this.getAppName();
	}
	
}

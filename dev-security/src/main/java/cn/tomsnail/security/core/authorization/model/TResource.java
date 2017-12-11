package cn.tomsnail.security.core.authorization.model;

/**
 *        资源
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午5:04:14
 * @see 
 */
public class TResource {
	
	public static final String URL = "url";
	
	public static final String RCODE = "rcode";

	private String code;
	
	private String type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isEquls(TResource resource){
		if(this.code.equals(resource.getCode())&&this.type.equals(resource.getType())){
			return true;
		}
		return false;
	}
	
}

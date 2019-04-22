package cn.tomsnail.snail.ext.security.core.limit;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午5:11:56
 * @see 
 */
public class LimitRule {

	public String resource;
	
	public int limitType;
	
	public int limitRate;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getLimitType() {
		return limitType;
	}

	public void setLimitType(int limitType) {
		this.limitType = limitType;
	}

	public int getLimitRate() {
		return limitRate;
	}

	public void setLimitRate(int limitRate) {
		this.limitRate = limitRate;
	}
	
	
	
}

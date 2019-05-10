package cn.tomsnail.snail.ext.security.core.limit;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午6:20:52
 * @see 
 */
public class TResourceUser {
	
	private LimitRule limitRule;

	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public LimitRule getLimitRule() {
		return limitRule;
	}

	public void setLimitRule(LimitRule limitRule) {
		this.limitRule = limitRule;
	}
	
	
	
}

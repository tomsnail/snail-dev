package cn.tomsnail.snail.ext.security.core.limit.store;

import cn.tomsnail.snail.ext.security.core.limit.LimitRule;
import cn.tomsnail.snail.ext.security.core.limit.TResourceUser;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午6:14:34
 * @see 
 */
public interface IRuleStore {

	
	public LimitRule getLimitRule(TResourceUser resource);
	
	public void saveRule(LimitRule limitRule);
	
}

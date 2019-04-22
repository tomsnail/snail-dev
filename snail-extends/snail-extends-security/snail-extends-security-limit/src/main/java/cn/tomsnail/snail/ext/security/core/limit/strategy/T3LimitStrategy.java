package cn.tomsnail.snail.ext.security.core.limit.strategy;

import cn.tomsnail.snail.ext.security.core.limit.TResourceUser;
import cn.tomsnail.snail.ext.security.core.limit.store.IRuleStore;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午6:01:18
 * @see 
 */
public class T3LimitStrategy implements ILimitStrategy{
	
	private IRuleStore ruleStore ;

	@Override
	public boolean doLimit(TResourceUser resource) {
	
		return false;
	}

	public IRuleStore getRuleStore() {
		return ruleStore;
	}

	public void setRuleStore(IRuleStore ruleStore) {
		this.ruleStore = ruleStore;
	}
	
	
	

	
}

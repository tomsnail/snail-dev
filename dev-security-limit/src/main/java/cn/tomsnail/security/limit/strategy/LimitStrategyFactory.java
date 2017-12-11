package cn.tomsnail.security.limit.strategy;

import java.util.Map;

import cn.tomsnail.security.limit.LimitRule;
import cn.tomsnail.security.limit.TResourceUser;
import cn.tomsnail.security.limit.store.IRuleStore;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午5:39:08
 * @see 
 */
public class LimitStrategyFactory implements IStrategyFactory{
	
	private IRuleStore ruleStore ;
	
	private Map<Integer,ILimitStrategy> limitStrategyMap;
	
	@Override
	public ILimitStrategy getLimitStrategy(TResourceUser resource) {
		LimitRule limitRule = ruleStore.getLimitRule(resource);
		if(limitRule!=null){
			resource.setLimitRule(limitRule);
			if(limitStrategyMap.containsKey(limitRule.getLimitType())){
				return limitStrategyMap.get(limitRule.getLimitType());
			}
		}
		return new DefaultLimitStrategy();
	}


	public IRuleStore getRuleStore() {
		return ruleStore;
	}


	public void setRuleStore(IRuleStore ruleStore) {
		this.ruleStore = ruleStore;
	}


	public Map<Integer, ILimitStrategy> getLimitStrategyMap() {
		return limitStrategyMap;
	}


	public void setLimitStrategyMap(Map<Integer, ILimitStrategy> limitStrategyMap) {
		this.limitStrategyMap = limitStrategyMap;
	}


	

	
	
	
	 
	
}

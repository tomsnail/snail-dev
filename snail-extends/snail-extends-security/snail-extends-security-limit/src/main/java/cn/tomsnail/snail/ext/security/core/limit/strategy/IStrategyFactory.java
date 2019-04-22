package cn.tomsnail.snail.ext.security.core.limit.strategy;

import cn.tomsnail.snail.ext.security.core.limit.TResourceUser;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午5:39:56
 * @see 
 */
public interface IStrategyFactory {

	public ILimitStrategy getLimitStrategy(TResourceUser resource);
	
}

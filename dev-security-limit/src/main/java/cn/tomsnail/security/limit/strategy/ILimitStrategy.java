package cn.tomsnail.security.limit.strategy;

import cn.tomsnail.security.limit.TResourceUser;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午5:31:02
 * @see 
 */
public interface ILimitStrategy {

	public boolean doLimit(TResourceUser resource);
	
	
	
}

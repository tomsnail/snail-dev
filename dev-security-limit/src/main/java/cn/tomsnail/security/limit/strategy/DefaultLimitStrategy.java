package cn.tomsnail.security.limit.strategy;

import cn.tomsnail.security.limit.TResourceUser;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午6:28:07
 * @see 
 */
public class DefaultLimitStrategy implements ILimitStrategy{

	@Override
	public boolean doLimit(TResourceUser resource) {
		return true;
	}

}

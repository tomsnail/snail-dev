package cn.tomsnail.security.limit.filter;

import cn.tomsnail.security.core.authorization.model.TResource;
import cn.tomsnail.security.core.fail.SecurityException;
import cn.tomsnail.security.limit.TResourceUser;
import cn.tomsnail.security.limit.strategy.ILimitStrategy;
import cn.tomsnail.security.limit.strategy.IStrategyFactory;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午5:32:13
 * @see 
 */
public class DefaultLimitFilter implements ILimitFilter {
	
	private IStrategyFactory strategyFactory;
	
	private IGetTResource getTResource;

	@Override
	public boolean doLimitFilter(Object obj) throws Exception {
		if(getTResource==null||strategyFactory==null){
			throw new SecurityException("DefaultLimitFilter properties can not is null!");
		}
		TResourceUser resource = getTResource.getTResource(obj);
		if(resource==null){
			throw new SecurityException("TResource can not is null!");
		}
		ILimitStrategy limitStrategy = strategyFactory.getLimitStrategy(resource);
		if(limitStrategy==null){
			throw new SecurityException("LimitStrategy can not is null!");
		}
		return limitStrategy.doLimit(resource);
	}

	public IStrategyFactory getStrategyFactory() {
		return strategyFactory;
	}

	public void setStrategyFactory(IStrategyFactory strategyFactory) {
		this.strategyFactory = strategyFactory;
	}

	public IGetTResource getGetTResource() {
		return getTResource;
	}

	public void setGetTResource(IGetTResource getTResource) {
		this.getTResource = getTResource;
	}

		

	
	

}

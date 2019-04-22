package cn.tomsnail.snail.ext.security.core.limit.filter;

import cn.tomsnail.snail.ext.security.core.filter.DefaultSecurityBasicFilter;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午5:29:01
 * @see 
 */
public class LimitSecurityBasicFilter extends DefaultSecurityBasicFilter implements ILimitFilter{

	private ILimitFilter limitFilter;
	
	@Override
	public boolean doFilter(Object obj){
		if(doLimitFilter(obj)){
			return super.doFilter(obj);
		}else{
			return false;
		}
	}

	@Override
	public boolean doLimitFilter(Object obj) {
		if(limitFilter!=null){
			try {
				return limitFilter.doLimitFilter(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public ILimitFilter getLimitFilter() {
		return limitFilter;
	}

	public void setLimitFilter(ILimitFilter limitFilter) {
		this.limitFilter = limitFilter;
	} 
	
	
	
}

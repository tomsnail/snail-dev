package cn.tomsnail.snail.ext.security.core.limit.filter;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午5:25:05
 * @see 
 */
public interface ILimitFilter  {
	
	public boolean doLimitFilter(Object obj) throws Exception;
	
}

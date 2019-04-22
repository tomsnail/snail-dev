package cn.tomsnail.snail.ext.security.core.limit.filter;

import cn.tomsnail.snail.ext.security.core.limit.TResourceUser;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月10日 下午5:48:06
 * @see 
 */
public interface IGetTResource {

	public TResourceUser getTResource(Object obj);
	
}

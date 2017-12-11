package cn.tomsnail.security.limit.filter;

import cn.tomsnail.security.core.authorization.model.TResource;
import cn.tomsnail.security.limit.TResourceUser;

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

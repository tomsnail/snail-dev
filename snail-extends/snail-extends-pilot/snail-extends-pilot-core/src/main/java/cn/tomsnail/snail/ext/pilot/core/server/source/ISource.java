package cn.tomsnail.snail.ext.pilot.core.server.source;

import java.util.Map;

import cn.tomsnail.snail.ext.pilot.core.model.ProxyInfo;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月7日 上午11:23:01
 * @see 
 */
public interface ISource {

	public Map<String,ProxyInfo> getSourceData();
	
}

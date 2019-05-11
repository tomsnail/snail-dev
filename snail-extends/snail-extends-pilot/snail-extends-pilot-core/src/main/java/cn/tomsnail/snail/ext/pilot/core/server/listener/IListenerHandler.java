package cn.tomsnail.snail.ext.pilot.core.server.listener;

import java.util.List;

import cn.tomsnail.snail.ext.pilot.core.server.IServer;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月6日 上午11:59:30
 * @see 
 */
public interface IListenerHandler {

	public void handler(String parentPath, List<String> currentChilds);
	
	public void close();
	
	public void addServer(IServer server);
	
}

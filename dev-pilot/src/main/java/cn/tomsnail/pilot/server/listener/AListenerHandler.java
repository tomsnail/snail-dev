package cn.tomsnail.pilot.server.listener;

import java.util.List;

import cn.tomsnail.pilot.server.IServer;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月6日 下午12:12:13
 * @see 
 */
public abstract class AListenerHandler implements IListenerHandler{

	private IServer server;
	
	public AListenerHandler(IServer server){
		this.server = server;
	}
	
	public void addServer(IServer server){
		this.server = server;
	}
	
	@Override
	public void handler(String parentPath, List<String> currentChilds) {
		server.notifly(parentPath, handler0(parentPath,currentChilds));
	}
	
	public abstract int handler0(String parentPath, List<String> currentChilds);
}

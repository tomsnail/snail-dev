package cn.tomsnail.snail.ext.pilot.core.server;

import java.util.ArrayList;
import java.util.List;

public class ServerManager {

	private List<IServer> servers = new ArrayList<IServer>();;
	
	public ServerManager(){
		IServer serviceServer = new ServiceServer();
		IServer nodeServer = new NodeServer(serviceServer);
		IServer proxyServer = new ProxyServer(serviceServer);
		servers.add(serviceServer);
		servers.add(nodeServer);
		servers.add(proxyServer);
	}
	
}

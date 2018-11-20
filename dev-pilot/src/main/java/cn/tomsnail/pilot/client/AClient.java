package cn.tomsnail.pilot.client;

import cn.tomsnail.pilot.model.ServiceProcess;


public abstract class AClient implements IClient{

	private IClient client;
	
	public AClient(IClient client){
		this.client = client;
	}
	
	
	public void init() {
		if(client==null) return;
		client.init();
	}

	
	public void start() {
		if(client==null) return;
		client.start();
	}

	
	public void stop() {
		if(client==null) return;
		client.stop();
	}

	
	public void destory() {
		if(client==null) return;
		client.destory();
	}

	
	public void register(Object info) {
		if(client==null) return;
		client.register(info);
	}
	
	
	public void send(Object info) {
		if(client==null) return;
		client.send(info);
	}
	
	
	public ServiceProcess getClientInfo(){
		if(client==null) return null;
		return client.getClientInfo();
	} 
}

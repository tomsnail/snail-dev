package cn.tomsnail.pilot.client;

import cn.tomsnail.pilot.model.ServiceProcess;


public abstract class AClient implements IClient{

	private IClient client;
	
	public AClient(IClient client){
		this.client = client;
	}
	
	@Override
	public void init() {
		if(client==null) return;
		client.init();
	}

	@Override
	public void start() {
		if(client==null) return;
		client.start();
	}

	@Override
	public void stop() {
		if(client==null) return;
		client.stop();
	}

	@Override
	public void destory() {
		if(client==null) return;
		client.destory();
	}

	@Override
	public void register(Object info) {
		if(client==null) return;
		client.register(info);
	}
	
	@Override
	public void send(Object info) {
		if(client==null) return;
		client.send(info);
	}
	
	@Override
	public ServiceProcess getClientInfo(){
		if(client==null) return null;
		return client.getClientInfo();
	} 
}

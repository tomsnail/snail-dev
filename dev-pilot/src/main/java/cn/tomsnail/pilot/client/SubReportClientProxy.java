package cn.tomsnail.pilot.client;

import cn.tomsnail.pilot.model.ServiceProcess;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 下午3:26:51
 * @see 
 */
public class SubReportClientProxy extends ReportClient implements ISubProxy{

	private ISubProxy subProxy;
	
	private int clientType = 0; //0 client 1 client and proxy
	
	public SubReportClientProxy(IClient client,IReportContent reportContent,ISubProxy subProxy) {
		super(client,reportContent);
		this.subProxy = subProxy;
		startThread();
	}
	
	public SubReportClientProxy(IClient client,IReportContent reportContent) {
		this(client,reportContent,null);
	}
	
	private void startThread(){
		Thread t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}

	
	public int startProxy(int clientType,String name) {
		if(subProxy==null) return 0;
		return this.subProxy.startProxy(clientType,name);
	}

	@SuppressWarnings("static-access")
	public void run() {
		init();
		ServiceProcess serviceProcess = getClientInfo();
		register(serviceProcess);
		clientType = startProxy(clientType,serviceProcess.getName());
		while(isRun()){
			try {
				Thread.currentThread().sleep(getSleepTime());
				send(reportContent.getContent());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ServiceProcess getClientInfo(){
		ServiceProcess serviceProcess = super.getClientInfo();
		if(this.getReportContent()!=null){
			serviceProcess.setReportContent(this.getReportContent().getContent());
		}
		return serviceProcess;
	}
}

package cn.tomsnail.snail.ext.pilot.core.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月6日 上午11:23:29
 * @see 
 */
public abstract class AServer implements IServer,Runnable{
	
	protected boolean isRun = true;	
	
	protected List<ICallback> callbacks = new ArrayList<ICallback>();

	ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	public AServer(){
		init();
		executorService.submit(this);
	}
	@Override
	public void run(){
		start();
	}
	@Override
	public void addCallback(ICallback callback){
		callbacks.add(callback);
	}
	
	protected void doCallback(Object obj){
		for(ICallback callback:callbacks){
			callback.call(obj);
		}
	}

	
}

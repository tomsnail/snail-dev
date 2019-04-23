package cn.tomsnail.snail.ext.pilot.hot.reload.spring;

import org.springframework.context.ApplicationContext;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月22日 上午9:38:23
	* @see 
	*/     
public class SpringHotLoadInit {
	
	private static SpringHotLoadInit instance;

	private ApplicationContext applicationContext;
	
	private static final byte[] lock = new byte[1];
	
	private SpringHotLoadInit(ApplicationContext applicationContext){
		this.applicationContext = applicationContext;
	}
	
	public static void init(ApplicationContext applicationContext){
		synchronized (lock) {
			if(instance==null){
				instance = new SpringHotLoadInit(applicationContext);
			}
		}
	}
	
	public static SpringHotLoadInit getInstance(){
		return instance;
	}
	
	
	public ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
}

package cn.tomsnail.hot.load.spring;


import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import cn.tomsnail.hot.load.annotation.HotLoad;
import cn.tomsnail.hot.load.file.DefaultRtFileCallBack;
import cn.tomsnail.hot.load.file.RtFileCallBack;
import cn.tomsnail.hot.load.m.HotLoadManager;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月22日 上午10:07:00
	* @see 
	*/     
@Component
@ComponentScan(basePackages={"cn.tomsnail.hot.load.m"})
public class SpringHotLoadBean extends ApplicationObjectSupport implements BeanPostProcessor{
	
	private String path = "C:\\Users\\yangsong\\Desktop\\hotload";
	
	private long interval = 2000; 
	
	private RtFileCallBack rtFileCallBack;
	
	@Autowired
	private HotLoadManager hotLoadManager;
	
	public SpringHotLoadBean(){
		
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		
		if(bean.getClass().isAnnotationPresent(HotLoad.class)){
			hotLoadManager.setValue(bean);
		}
		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
	

	public RtFileCallBack getRtFileCallBack() {
		return rtFileCallBack;
	}

	public void setRtFileCallBack(RtFileCallBack rtFileCallBack) {
		this.rtFileCallBack = rtFileCallBack;
	}

	@PostConstruct
	public void start(){
		SpringHotLoadInit.init(this.getApplicationContext());
		rtFileCallBack = new DefaultRtFileCallBack(SpringHotLoadInit.getInstance().getApplicationContext().getClassLoader(),this.hotLoadManager);
		hotLoadManager.start(path, interval, rtFileCallBack);;
	
	}
	
	
}

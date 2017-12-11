package cn.tomsnail.starter.domain.spring;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import cn.tomsnail.starter.domain.ISpringService;
import cn.tomsnail.starter.domain.SpringServiceManager;

/**
 *  spring bean初始化时加载启动管理器      
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月12日 下午3:04:20
 * @see 
 */

@Component
public class StarterBeanPostProcessor implements BeanPostProcessor{

	private List<ISpringService> services = new ArrayList<ISpringService>();
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if(bean.getClass().getTypeName().startsWith("cn.tomsnail.starter.domain.SpringServiceManager")){
			SpringServiceManager springServiceManager = (SpringServiceManager) bean;
			springServiceManager.setServices(services);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
			if(!bean.getClass().getTypeName().equals("cn.tomsnail.starter.domain.SpringServiceManager")){
				Class[] classes = bean.getClass().getInterfaces();
				if(classes!=null&&classes.length>0){
					for(Class clazz:classes){
						if(clazz.getTypeName().equals("cn.tomsnail.starter.domain.ISpringService")){
							services.add((ISpringService) bean);
							break;
						}
					}
				}
			}
		return bean;
	}

	
}

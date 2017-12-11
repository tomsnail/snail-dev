package cn.tomsnail.lock.spring;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import cn.tomsnail.config.client.plugin.AnnotationConverter;
import cn.tomsnail.lock.core.ILock;
import cn.tomsnail.lock.core.ILockFactory;
import cn.tomsnail.lock.core.LockConfig;
import cn.tomsnail.lock.core.LockFactory;
import cn.tomsnail.lock.core.annotation.Lock;

/**
 *        spring初始化锁
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午12:19:35
 * @see 
 */
@Component
public class LockBeanPostProcessor implements BeanPostProcessor{

	private ILockFactory lockFactory = new LockFactory();
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		Class clazz = bean.getClass();
		Field[] fs = clazz.getDeclaredFields();
		for(Field f:fs){
			if(f.getType().getCanonicalName().startsWith("cn.tomsnail.lock.core.ILock")&&f.isAnnotationPresent(Lock.class)){
				Lock lcok = f.getAnnotation(Lock.class);
				LockConfig lockConfig = new LockConfig();
				lockConfig.setLockName(lcok.name());
				lockConfig.setLockTarget(lcok.target());
				lockConfig.setTimeout(AnnotationConverter.getValue(lcok.timeout()));
				lockConfig.setUrl(AnnotationConverter.getValue(lcok.url()));
				lockConfig.setUsername(AnnotationConverter.getValue(lcok.username()));
				lockConfig.setPassword(AnnotationConverter.getValue(lcok.password()));
				ILock lock = lockFactory.getLock(lockConfig);
				f.setAccessible(true);
				try {
					f.set(bean, lock);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public ILockFactory getLockFactory() {
		return lockFactory;
	}

	public void setLockFactory(ILockFactory lockFactory) {
		this.lockFactory = lockFactory;
	}
	
	

}

package cn.tomsnail.task.elastic.job;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ElasticJob;

@Component
@ComponentScan(basePackages={"cn.tomsnail.task.elastic.job"})
public class SpringElasticJobBeanPostProcessor  implements BeanPostProcessor{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)throws BeansException {
		Class clazz = bean.getClass();
		if(clazz.isAnnotationPresent(SpringElasticJob.class)){
			Class[] clazzs = clazz.getInterfaces();
			if(clazzs!=null&&clazzs.length>0){
				for(Class _clazz:clazzs){
					if(_clazz.getCanonicalName().equals("com.dangdang.ddframe.job.api.simple.SimpleJob")){
						SpringElasticJob springElasticJob = bean.getClass().getAnnotation(SpringElasticJob.class);
						SpringElasticJobScheduler.ELASTIC_JOBS.add(new SpringElasticJobM(springElasticJob, (ElasticJob) bean));
						break;
					}
				}
			}
		}
		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)throws BeansException {
		return bean;
	}

}

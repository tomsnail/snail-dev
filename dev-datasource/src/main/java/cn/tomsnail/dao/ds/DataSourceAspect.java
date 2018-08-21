package cn.tomsnail.dao.ds;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.tomsnail.dao.ds.router.DataSourceRoute;

@Aspect
@Order(1)
@Component
@ComponentScan(basePackages="cn.tomsnail.dao.ds.router")
public class DataSourceAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
	
	

	
	@Autowired(required=false)
	private List<DataSourceRoute> dataSourceRoutes;
	
	
	@Pointcut("execution(public * com..*.dao..*.*(..))")
	public void dataSourceSelect(){
	        
	};

	@Before("dataSourceSelect()")
	public void before(JoinPoint point)  
    {         
         
         Object target = point.getTarget();  
         String method = point.getSignature().getName();  
         
        
         
         Class<?>[] classz = target.getClass().getInterfaces();  
         if(classz==null||classz.length==0){
        	 return;
         }
         DataSource data = null;
         
         DataSourceName annotationDataSourceName = null;
         
         for(Class<?> clazz:classz){
        	 if(clazz.getName().contains("PageDao")||clazz.getName().contains("BaseDao")){
        		 continue;
        	 }
             try {  
            	 if(clazz.isAnnotationPresent(DataSource.class)){
            		 data = clazz.getAnnotation(DataSource.class);
            	 }
            	 Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())  
                         .getMethod().getParameterTypes();  
                 Method m = clazz.getMethod(method, parameterTypes);  
                 if (m != null && m.isAnnotationPresent(DataSource.class)) {  
                     data = m.getAnnotation(DataSource.class);  
                 }   
                 if(data!=null){
                	 annotationDataSourceName = new DataSourceName(data.value(), data.weight());
                	 break;
                 }
             } catch (Exception e) {  
            	 logger.error("", e);
             }  
         }
         DataSourceName dataSourceName = annotationDataSourceName;
         

         if(dataSourceName==null){
        	 dataSourceName = new DataSourceName(RountingDataSource.DEFAULT_DSN,0);
         }
         if(dataSourceRoutes!=null&&!dataSourceRoutes.isEmpty()){
    		 for(DataSourceRoute dsr:dataSourceRoutes){
    			 try {
    				DataSourceName _dataSourceName = dsr.route(point, RountingDataSource.routeType,annotationDataSourceName);
    				if(_dataSourceName==null){
    					continue;
    				}
    				if(dataSourceName.lt(_dataSourceName)){
    					dataSourceName = _dataSourceName;
    				}
				} catch (Exception e) {
					logger.error("", e);
				}
    		 }
    	 }
         HandleDataSource.putDataSource(dataSourceName.getName());
        
    }

	public List<DataSourceRoute> getDataSourceRoutes() {
		return dataSourceRoutes;
	}

	public void setDataSourceRoutes(List<DataSourceRoute> dataSourceRoutes) {
		this.dataSourceRoutes = dataSourceRoutes;
	}  
	
	
	
}

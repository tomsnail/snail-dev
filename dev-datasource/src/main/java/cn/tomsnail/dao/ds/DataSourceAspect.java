package cn.tomsnail.dao.ds;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
	
	
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
                	 break;
                 }
             } catch (Exception e) {  
            	 logger.error("", e);
             }  
         }
         
         if(data==null){
        	 if(RountingDataSource.ROUTE_AUTO_WR.equals(RountingDataSource.routeType)){
            	 if(method.startsWith("find")||method.startsWith("get")||method.startsWith("query")||method.startsWith("count")||method.startsWith("page")){
            		 HandleDataSource.putDataSource("read");
            		 return;
            	 }
            	 if(method.startsWith("save")||method.startsWith("insert")||method.startsWith("delete")||method.startsWith("del")||method.startsWith("update")){
            		 HandleDataSource.putDataSource("write");
            		 return;
            	 }
             }
        	 HandleDataSource.putDataSource("default");
         }else{
        	 HandleDataSource.putDataSource(data.value());
         }
        
    }  
	
}

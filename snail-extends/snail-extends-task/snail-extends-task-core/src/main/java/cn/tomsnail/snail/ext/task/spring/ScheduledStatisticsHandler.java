package cn.tomsnail.snail.ext.task.spring;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.log.ls.FileLogFactory;
import cn.tomsnail.snail.core.log.ls.LogSystem;



@Aspect
@Component
public class ScheduledStatisticsHandler {
	
	
		private static final LogSystem LOG_SYSTEM = FileLogFactory.instance().create("scheduled");
	 	
	    @Pointcut("@annotation(cn.tomsnail.task.spring.annotation.Task)")  
	    public void proxyAspect() {

	    }

	    @Around("proxyAspect()")
	    public Object doInvoke(ProceedingJoinPoint joinPoint) throws Throwable{
	        Date date = new Date();
	        long start = System.currentTimeMillis();
	        Object result = joinPoint.proceed();
	        long end = System.currentTimeMillis();
	        Object target = joinPoint.getTarget();
	        LOG_SYSTEM.log("[{}] {} start at {} and end at {} cost time is {}", date,target.getClass().getName(),start,end,end-start);
	        return result;
	    }

}

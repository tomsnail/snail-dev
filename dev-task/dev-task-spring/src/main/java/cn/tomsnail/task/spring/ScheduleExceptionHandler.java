package cn.tomsnail.task.spring;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.tomsnail.log.ls.FileLogFactory;
import cn.tomsnail.log.ls.LogSystem;
import cn.tomsnail.util.ex.Exceptions;

@Aspect
@Component
public class ScheduleExceptionHandler {
	
	private static final LogSystem LOG_SYSTEM = FileLogFactory.instance().create("scheduled");
	
	@Pointcut("@annotation(cn.tomsnail.task.spring.annotation.Task)")  
    public void proxyAspect() {

    }

    @AfterThrowing(pointcut="proxyAspect()",throwing="ex")
    public void doException(JoinPoint joinPoint,Exception ex){
        Object target = joinPoint.getTarget();
        this.saveException(target, ex);
    }

    public void saveException(Object target,Exception ex){
        try {
        	
             LOG_SYSTEM.log("[{}] {} error is {}", new Date(),target.getClass().getName(),Exceptions.getStackTraceAsString(ex));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

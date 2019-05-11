package cn.tomsnail.snail.core.framework.validator;

import cn.tomsnail.core.util.validator.ValidatorFactory;
import cn.tomsnail.snail.core.config.client.ConfigClientFactory;
import cn.tomsnail.snail.core.config.client.plugin.AnnotationConverter;
import cn.tomsnail.snail.core.http.CommonMessage;
import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;
import cn.tomsnail.snail.core.log.Log;
import cn.tomsnail.snail.core.log.LogService;
import cn.tomsnail.snail.core.log.annotation.LogLevel;
import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.core.log.ls.ExceptionUtil;
import cn.tomsnail.snail.core.starter.AppMain;
import cn.tomsnail.snail.core.util.JsonUtil;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.ex.Exceptions;
import cn.tomsnail.snail.core.util.string.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * 基于aop的日志记录spring实现
 *
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:47:02
 * @see
 */
@Aspect
@Component
@Order(2)
public class ValidatorAspect {

    boolean valid = ConfigHelp.getInstance("config").getLocalConfig("system.valid",true);

    String validType = ConfigHelp.getInstance("config").getLocalConfig("system.valid.type","");


    //本地异常日志记录对象    
    private static final Logger logger = LoggerFactory.getLogger(ValidatorAspect.class);


    //Service层切点    
    @Pointcut("@annotation(cn.tomsnail.snail.core.framework.validator.SnailValidator)")
    public void validatorAspect() {
    }


    @Before("validatorAspect()")
    public void befor(JoinPoint joinPoint) {
        if(!valid){
            return;
        }
        Object[] os = joinPoint.getArgs();
        if(os==null||os.length==0){
            return;
        }
        for(Object o:os){
            if(o instanceof RequestData){
                RequestData requestData = (RequestData) o;
                Object body = requestData.getBody();
                if(body==null){
                    continue;
                }
                if(body instanceof Map){
                    try {
                        Map map = (Map) body;
                        SnailValidator snailValidator = getSnailValidator(joinPoint);
                        if(snailValidator!=null&&snailValidator.value()&&snailValidator.mapClass()!=null){
                            Class targetClass = snailValidator.mapClass();
                            Object t = ValidatorFactory.getHibernateObjectValidator().getValidBean(map,targetClass);
                            requestData.setTargetObj(t);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }else{
                    ValidatorFactory.getHibernateObjectValidator().valid(body);
                }
            }
        }

    }

    public  static SnailValidator getSnailValidator(JoinPoint joinPoint)
            throws Exception {

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    if(method.isAnnotationPresent(SnailValidator.class)){
                        return method.getAnnotation(SnailValidator.class);
                    }else{
                        return null;
                    }
                }
            }
        }
        return null;
    }


}    

package cn.tomsnail.snail.core.log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;























import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.config.client.ConfigClientFactory;
import cn.tomsnail.snail.core.config.client.plugin.AnnotationConverter;
import cn.tomsnail.snail.core.http.CommonMessage;
import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;
import cn.tomsnail.snail.core.log.annotation.LogLevel;
import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.core.log.ls.ExceptionUtil;
import cn.tomsnail.snail.core.starter.AppMain;
import cn.tomsnail.snail.core.util.JsonUtil;
import cn.tomsnail.snail.core.util.ex.Exceptions;
import cn.tomsnail.snail.core.util.string.StringUtils;






/**
 *        基于aop的日志记录spring实现
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:47:02
 * @see 
 */
@Aspect    
@Component
@Order(1)
public class SystemLogAspect {
	
	@Resource(name="DefaultListaLogService")
	private LogService logService;
	
	  
    //本地异常日志记录对象    
     private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect. class);    
     
    
    //Service层切点    
    @Pointcut("@annotation(cn.tomsnail.snail.core.log.annotation.LogPoint)")    
     public  void serviceAspect() {              
    }    
    
    /**  
     * 异常通知 用于拦截service层记录异常日志  
     *  
     * @param joinPoint  
     * @param e  
     */    
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")    
     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
    }    


    
    @Around("serviceAspect()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    	
    	boolean isDebug = false;
    	Object t = null;
		try {
			LogPoint logPoint = getLogPoint(joinPoint);
			ResultData _t = null;
			if(logPoint==null){//没有注解
				t = joinPoint.proceed();
			}else{
				int level = getBLevel(logPoint);
				if(level==Log.NOLOG){//不记录日志
					t = joinPoint.proceed();
				}else{
					if(level==Log.DEBUG){
						isDebug = true;
					}
					StringBuffer runinfo = new StringBuffer();
					Log log = new Log();
					long startTime = System.currentTimeMillis();
					try {
						if(isDebug){
							logger.info(logPoint.desc()+" start ");
						}
						t = joinPoint.proceed();
						if(t!=null&&t instanceof ResultData){
							ResultData resultData = (ResultData) t;
							if(StringUtils.isNotBlank(resultData.getStatus())){
								if(resultData.getStatus().equals(CommonMessage.FAILED)){
									log.setLevel(Log.ERROR);
									log.setThrowable(new Exception(resultData.getErrorMsg()+""));
									log.setResults(new StringBuffer(resultData.getMsg()+""));
									resultData.setErrorMsg("");
								}else if(!resultData.getStatus().equals(CommonMessage.SUCCESS)){
									log.setLevel(Log.B_ERROR);
									log.setResults(new StringBuffer(resultData.getMsg()+""));
								}
							}
							
						}
					} catch (Throwable ex) {
						runinfo.append(Exceptions.getStackTraceAsString(ex));
						logger.error(runinfo.toString());
						log.setLevel(Log.ERROR);
						log.setThrowable(ex);
						isDebug = true;
						
						if(logPoint.level().equals(LogLevel.DEBUG)){
							logger.error("",ex);
						}
						_t = new ResultData<>();
						_t.setStatus(CommonMessage.FAILED);
						_t.setMsg("application error");
						String em = ex.getMessage();
						if(em!=null&&(em.contains("SQL")||em.contains("Exception"))) {
							_t.setErrorMsg("Exception");
						}else {
							_t.setErrorMsg(em);
						}
						
						if(t  instanceof ResultData) {
							
						}
						else {
							
							_t.putBody("exResult", JsonUtil.toJson(t));
						}
						
					}
					long endTime = System.currentTimeMillis();
					log.addContent(runinfo.toString());
					
					
					
					if(_t !=null) {
						if(t==null){
							t = _t;
						}
						log(joinPoint, isDebug, _t, logPoint, log,startTime,endTime);

					}else if(t==null) {
						log(joinPoint, isDebug, "null", logPoint, log,startTime,endTime);

					}else {
						log(joinPoint, isDebug, t, logPoint, log,startTime,endTime);
					}
					if(logPoint.level().equals(LogLevel.DEBUG)){
						logger.info(logPoint.desc()+" end ");
					}
				}
			}
		} catch (Exception e) {
			ExceptionUtil.log(e);
		}
		return t;
	}

	  
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2016年12月13日 下午2:27:50
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
		
	protected void log(ProceedingJoinPoint joinPoint, boolean isDebug,Object t, LogPoint logPoint, Log log,long startTime,long endTime) throws IOException, JsonGenerationException,
			JsonMappingException, UnsupportedEncodingException {
		if(isDebug&&t!=null){
			StringBuffer parmsb = new StringBuffer();
			Object[] os = joinPoint.getArgs() ;
			if (os !=  null && os.length > 0) {    
				for(Object o:os){
					parmsb.append(JsonUtil.toJson(o)); 
				}
			} 
			log.addParams(parmsb.toString());
			log.addResult(JsonUtil.toJson(t));
			log.setLevel(Log.DEBUG);
			ExceptionUtil.log("params:{},result:{},exception:{}",log.getParams(),log.getResults(),log.getContent());
		}
		
		log.setDesc(new String(logPoint.desc().getBytes(),"UTF-8"));
		log.setModule(AppMain.AppName);
		log.setShared(logPoint.shared());
		log.setClassName(joinPoint.getTarget().getClass().getName());
		log.setMethodName(joinPoint.getSignature().getName());
		log.setTarget(logPoint.target());
		log.setEndTime(endTime+"");
		log.setCostTime((endTime-startTime)+"");
		log.setStartTime(startTime+"");
		logService.log(log);
	}

	  
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2016年12月13日 下午2:27:43
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
		
	protected int getBLevel(LogPoint logPoint) {
		String levelStr = null;
		 try {
			 levelStr = ConfigClientFactory.getInstance().getConfigClient().getConfig("system.log.level","");
		 } catch (Exception e) {
			 System.err.println(e.getMessage());
		 }
		 int level =  Log.INFO;
		 if(StringUtils.isNotBlank(levelStr)&&NumberUtils.isNumber(levelStr)){
			 level = Integer.parseInt(levelStr);
		 }else{
				levelStr = logPoint.level();
				if(levelStr.contains("${")){
					try {
						level = AnnotationConverter.getValue(levelStr, Integer.class,Log.INFO);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}else{
					if(NumberUtils.isNumber(levelStr))
						level = Integer.parseInt(levelStr);
				}
		 }
		return level;
	}
    
    
    /**  
     * 获取注解中对方法的描述信息 用于service层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static LogPoint getLogPoint(ProceedingJoinPoint joinPoint)    
             throws Exception {    
    	 boolean isLogger = false;
    	 try {
    		 isLogger = Boolean.valueOf(ConfigClientFactory.getInstance().getConfigClient().getConfig("system.islogger","true"));
		 } catch (Exception e) {
			 System.err.println(e.getMessage());
		 }
    	if(!isLogger) return null;
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                	 if(method.isAnnotationPresent(LogPoint.class)){
                         return method.getAnnotation(LogPoint.class);  
                	 }else{
                		 return null;
                	 }
                }    
            }    
        }    
        return null;    
    }

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	
   
}    

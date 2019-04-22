package cn.tomsnail.snail.ext.task.client;

import java.lang.reflect.Method;
import java.util.List;

import cn.tomsnail.snail.ext.task.client.annotation.TaskCallMethod;
import cn.tomsnail.snail.ext.task.client.annotation.TaskCallType;






/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月25日 下午1:40:10
 * @see 
 */
public class AnnotationTaskCall implements TaskCallScheduler{

	private List<String> classNames;
	
	private  List<TaskCall> taskCalls;
	
	private List<Object> objects;
	
	private MutilTaskCall mutilTaskCall = new MutilZookeeperTaskCall();
	
	public void init(){
		initClass();
		initInstance();
	}
	
	public void initClass(){
		if(classNames==null||classNames.size()<1){
			return;
		}
		for(String className:classNames){
			try {
				Method[] ms = Class.forName(className).getDeclaredMethods();
				for(final Method m:ms){
					if(!m.isAccessible()){
						continue;
					}
					if(m.isAnnotationPresent(TaskCallMethod.class)){
						TaskCallMethod taskCallMethod = m.getAnnotation(TaskCallMethod.class);
						int type = taskCallMethod.type();
						long time = taskCallMethod.time();
						this.scheduler(taskCallMethod.name(),new AnnotationTaskCallClassName(className, m.getName()), type, time);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	}
	
	public void initObjects(){
		if(objects==null||objects.size()<1){
			return;
		}
		for(Object obj:objects){
			try {
				Method[] ms = obj.getClass().getDeclaredMethods();
				for(final Method m:ms){
					if(!m.isAccessible()){
						continue;
					}
					if(m.isAnnotationPresent(TaskCallMethod.class)){
						TaskCallMethod taskCallMethod = m.getAnnotation(TaskCallMethod.class);
						int type = taskCallMethod.type();
						long time = taskCallMethod.time();
						this.scheduler(taskCallMethod.name(),new AnnotationTaskCallObject(obj, m.getName()), type, time);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	}
	
	public void initInstance(){
		if(taskCalls==null||taskCalls.size()<1){
			return;
		}
		for(TaskCall taskCall:taskCalls){
			Class clazz = taskCall.getClass();
			if(clazz.isAnnotationPresent(TaskCallType.class)){
				TaskCallType taskCallType = (TaskCallType) clazz.getAnnotation(TaskCallType.class);
				int type = taskCallType.type();
				long time = taskCallType.time();
				scheduler(taskCallType.name(),taskCall,type,time);
			}
		}
	}
	
	public <T> void scheduler(String name,TaskCall<T> taskCall,int type,long time){
		if(name==null||name.equals("")){
			name = taskCall.getClass().getCanonicalName()+""+System.currentTimeMillis();
		}
		mutilTaskCall.register(name, taskCall, type, time);
	}

	public List<String> getClassNames() {
		return classNames;
	}

	public void setClassNames(List<String> classNames) {
		this.classNames = classNames;
	}

	public List<TaskCall> getTaskCalls() {
		return taskCalls;
	}

	public void setTaskCalls(List<TaskCall> taskCalls) {
		this.taskCalls = taskCalls;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	public MutilTaskCall getMutilTaskCall() {
		return mutilTaskCall;
	}

	public void setMutilTaskCall(MutilTaskCall mutilTaskCall) {
		this.mutilTaskCall = mutilTaskCall;
	}
	
	
	
	
}

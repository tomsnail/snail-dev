package cn.tomsnail.snail.ext.task.client;

import java.lang.reflect.Method;

public  class AnnotationTaskCallClassName implements TaskCall<Object>{

	private String className;
	
	private String methodName;
	
	public AnnotationTaskCallClassName(String className,String methodName){
		this.className = className;
		this.methodName = methodName;
	}

	@Override
	public Object call() {
		Object result = "";
		try {
			Class clazz = Class.forName(className);
			Method[] ms = clazz.getDeclaredMethods();
			for(Method m:ms){
				if(!m.isAccessible()&&!m.getName().equals(this.methodName)){
					continue;
				}
				result = m.invoke(clazz.newInstance(), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}

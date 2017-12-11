package cn.tomsnail.task.client;

import java.lang.reflect.Method;

public  class AnnotationTaskCallObject implements TaskCall<Object>{

	private Object obj;
	
	private String methodName;
	
	public AnnotationTaskCallObject(Object obj,String methodName){
		this.obj = obj;
		this.methodName = methodName;
	}

	@Override
	public Object call() {
		Object result = "";
		try {
			Class clazz = obj.getClass();
			Method[] ms = clazz.getDeclaredMethods();
			for(Method m:ms){
				if(!m.isAccessible()&&!m.getName().equals(this.methodName)){
					continue;
				}
				result = m.invoke(obj, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}

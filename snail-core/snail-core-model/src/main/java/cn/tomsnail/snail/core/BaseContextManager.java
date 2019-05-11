package cn.tomsnail.snail.core;

public class BaseContextManager {
	
	public static final ThreadLocal<BaseContext> LOCAL_CONTEXT = new ThreadLocal<BaseContext>();
	
	public static String get(String key){
		BaseContext context = LOCAL_CONTEXT.get();
		if(context==null){
			return null;
		}
		return context.getContext().get(key);
	}
	
	public static void set(BaseContext context){
		LOCAL_CONTEXT.set(context);
	}

}

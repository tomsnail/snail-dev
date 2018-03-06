package cn.tomsnail.framwork.core;

public class BaseContextManager {
	
	public static final ThreadLocal<BaseContext> LOCAL_CONTEXT = new ThreadLocal<BaseContext>();

}

package cn.tomsnail.log.ls;

import cn.tomsnail.util.ex.Exceptions;

public class ExceptionUtil {
	
	private static final LogSystem EXCEPTION = FileLogFactory.instance().create("exception");
	
	public static void log(Throwable ex) {
		EXCEPTION.log(Exceptions.getStackTraceAsString(ex));
	}
	
	public static void log(String message) {
		EXCEPTION.log(message);
	}
	
	public static void log(String message,Object...objects) {
		EXCEPTION.log(message,objects);
	}
	
	public static void log(String message,Throwable ex) {
		EXCEPTION.log(message+" :{}",Exceptions.getStackTraceAsString(ex));
	}

}

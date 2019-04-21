package cn.tomsnail.snail.core.log.ls;

public interface LogSystem {
	
	public void log(String message);
	
	public void log(String message, Object...objects );
	
	
}

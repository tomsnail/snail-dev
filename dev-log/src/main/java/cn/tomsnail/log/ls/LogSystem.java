package cn.tomsnail.log.ls;

public interface LogSystem {
	
	public void log(String context);
	
	public void log(String message, Object...objects );
	
	
}

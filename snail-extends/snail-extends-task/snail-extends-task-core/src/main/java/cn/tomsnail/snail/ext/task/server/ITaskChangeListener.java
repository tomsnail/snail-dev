package cn.tomsnail.snail.ext.task.server;

public interface ITaskChangeListener {
	
	public void changed(String name,String value,int type);

}

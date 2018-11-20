package cn.tomsnail.task.server;

public interface ITaskChangeListener {
	
	public void changed(String name,String value,int type);

}

package cn.tomsnail.task.server;

import java.util.List;

public class DefaultTaskChangeListener implements ITaskChangeListener {
	private List<ITaskChangeListener> changeListeners;
	@Override
	public void changed(String path, String value, int type) {
		// TODO Auto-generated method stub
		if(changeListeners.size()>0){
			for(ITaskChangeListener changeListener:changeListeners){
				changeListener.changed(path, value, type);
			}
		}

	}
	public List<ITaskChangeListener> getChangeListeners() {
		return changeListeners;
	}
	public void setChangeListeners(List<ITaskChangeListener> changeListeners) {
		this.changeListeners = changeListeners;
	}

}

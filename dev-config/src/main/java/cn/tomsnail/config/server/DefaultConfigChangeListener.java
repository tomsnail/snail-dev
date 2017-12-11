package cn.tomsnail.config.server;

import java.util.List;

/**
 *        默认的配置变化监听者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午3:54:28
 * @see 
 */
public class DefaultConfigChangeListener implements IConfigChangeListener{
	
	/**
	 * 监听者列表
	 */
	private List<IConfigChangeListener> changeListeners;

	@Override
	public void changed(String path, String value, int type) {
		if(changeListeners!=null){
			for(IConfigChangeListener changeListener:changeListeners){
				changeListener.changed(path, value, type);
			}
		}
	}

	public List<IConfigChangeListener> getChangeListeners() {
		return changeListeners;
	}

	public void setChangeListeners(List<IConfigChangeListener> changeListeners) {
		this.changeListeners = changeListeners;
	}
	
	

}

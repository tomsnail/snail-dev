package cn.tomsnail.snail.core.config.server.s;

import java.util.ArrayList;
import java.util.List;

import cn.tomsnail.snail.core.config.server.IConfigObserver;
import cn.tomsnail.snail.core.config.server.IConfigSource;





/**
 *        抽象配置源类，用于定义内部结构
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月20日 下午1:50:49
 * @see 
 */
public abstract class AConfigSource implements IConfigSource{

	/**
	 * 配置源的观察者，配置源变化，则要通知各个观察者
	 */
	private List<IConfigObserver> configObservers = new ArrayList<IConfigObserver>();
	
	public AConfigSource(){
		
	}
	
	public AConfigSource(IConfigObserver configObserver){
		configObservers.add(configObserver);
	}
	
	public void addObserver(IConfigObserver configObserver){
		configObservers.add(configObserver);
	}
	
	public void removeObserver(IConfigObserver configObserver){
		configObservers.remove(configObserver);
	}
	
	/**
	 *        通知所有观察者
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:49:41
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	protected void notifyObservers(){
		for(IConfigObserver configObserver:configObservers){
			configObserver.refresh();
		}
	}

}

package cn.tomsnail.snail.core.util.thread.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听管理
 * @author yangsong
 * @date 2012-5-18 上午9:43:44
 */
public class ListenerManager {
	
	/**
	 * 监听服务容器
	 * @author yangsong
	 * @date 2012-5-18 上午9:43:55
	 */
	private static List<Listener> listeners = new ArrayList<Listener>();
	
	/**
	 * 关闭所以监听
	 * @author yangsong
	 * @date 2012-5-18 上午9:44:09
	 */
	public synchronized void close(){
		for(Listener listener:listeners){
			if(listener.isListend()){
				listener.stopd();
			}
		}
	}
	/**
	 * 启动所以监听
	 * @author yangsong
	 * @date 2012-5-18 上午9:44:23
	 */
	public synchronized void start(){
		for(Listener listener:listeners){
			if(!listener.isListend()){
				listener.start();
			}
		}
	}
	/**
	 * 启动指定的监听
	 * @author yangsong
	 * @date 2012-5-18 上午9:44:34
	 */
	public synchronized void start(ListenerEvent event){
		for(Listener listener:listeners){
			if(listener.equlasEvent(event)){
				if(!listener.isListend()){
					listener.start();
				}
			}
		}
	}
	/**
	 * 移除指定监听
	 * @author yangsong
	 * @date 2012-5-18 上午9:44:53
	 */
	public synchronized void remove(ListenerEvent event){
		for(int i=0;i<listeners.size();i++){
			Listener listener = listeners.get(i);
			if(listener.equlasEvent(event)){
				listener.stopd();
				listeners.remove(listener);
				break;
			}
		}
	}
	/**
	 * 关闭指定监听
	 * @author yangsong
	 * @date 2012-5-18 上午9:45:05
	 */
	public synchronized void close(ListenerEvent event){
		for(int i=0;i<listeners.size();i++){
			Listener listener = listeners.get(i);
			if(listener.equlasEvent(event)){
				listener.stopd();
				break;
			}
		}
	}
	/**
	 * 移除所以监听
	 * @author yangsong
	 * @date 2012-5-18 上午9:45:21
	 */
	public synchronized void removeAll(){
		for(int i=0;i<listeners.size();i++){
			Listener listener = listeners.get(i);
			listener.stopd();
		}
		listeners.clear();
	}
	/**
	 * 添加监听事件
	 * @author yangsong
	 * @date 2012-5-18 上午9:45:34
	 */
	public synchronized void addListener(ListenerEvent event){
		Listener listener = new Listener(event);
		ListenerManager.listeners.add(listener);
	}
	/**
	 * 添加监听事件集合
	 * @author yangsong
	 * @date 2012-5-18 上午9:45:46
	 */
	public void addListeners(List<ListenerEvent> list){
		if(list!=null){
			for(int i=0;i<list.size();i++){
				addListener(list.get(i));
			}
		}
	}
	
	public void setList(List<ListenerEvent> list) {
		addListeners(list);
	}
}

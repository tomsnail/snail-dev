package cn.tomsnail.util.thread.listener;

/**
 * 监听服务
 * @author yangsong
 * @date 2012-5-18 上午9:42:11
 */
public class Listener extends Thread{

	/**
	 * 监听启动标志
	 * @author yangsong
	 * @date 2012-5-18 上午9:42:32
	 */
	private boolean listend = false;
	
	/**
	 * 监听事件
	 * @author yangsong
	 * @date 2012-5-18 上午9:42:44
	 */
	private ListenerEvent event;
	
	public Listener(ListenerEvent event){
		this.event = event;
	}
	
	@Override
	public void run() {
		listend = true;
		while(listend){
			event.execute();
		}
	}

	/**
	 * @return the listend
	 */
	public boolean isListend() {
		return listend;
	}

	/**
	 * @param listend the listend to set
	 */
	public void setListend(boolean listend) {
		this.listend = listend;
	}
	
	
	public boolean equlasEvent(ListenerEvent event){
		if(this.event==event){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 停止监听
	 * @author yangsong
	 * @date 2012-5-18 上午9:42:58
	 */
	public void stopd(){
		this.listend = false;
	}
	
}

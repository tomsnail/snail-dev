package cn.tomsnail.task.server;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 下午4:10:03
 * @see 
 */
public interface IContainer<T> {

	public void init();
	
	public void clear();
	
	public void add(T t);
	
	public void remove(T t);
	
	public void destory();
	
	public void refresh();
	
	public T take();
	
	public boolean contains(T t);
	
}

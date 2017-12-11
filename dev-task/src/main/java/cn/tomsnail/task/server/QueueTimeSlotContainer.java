package cn.tomsnail.task.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 下午4:04:43
 * @see 
 */
public class QueueTimeSlotContainer implements IContainer<TimeSlot>{

	private static final BlockingQueue<TimeSlot> QUEUE = new LinkedBlockingQueue<TimeSlot>();

	@Override
	public void clear() {
		QUEUE.clear();
		
	}

	@Override
	public void add(TimeSlot t) {
		System.out.println("task "+t.getName()+" added ");
		try {
			QUEUE.put(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(TimeSlot t) {
		QUEUE.remove(t);
	}

	@Override
	public void destory() {
		clear();
	}
	
	public TimeSlot take(){
		try {
			return QUEUE.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void refresh() {
		
	}

	@Override
	public boolean contains(TimeSlot t) {
		return QUEUE.contains(t);
	}
	
	
	
}

package cn.tomsnail.snail.ext.task.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 下午4:29:11
 * @see 
 */
public abstract class ATimeSlotScheduler  implements IScheduler{
	
	protected IContainer<TimeSlot> container = new QueueTimeSlotContainer();
	
	protected boolean isRun = true;
	
	protected long rate = 10l;

	private static final Logger LOGGER = LoggerFactory.getLogger(ATimeSlotScheduler.class);

	@Override
	public void run() {
		while(isRun()){
			try {
				LOGGER.info("[ATimeSlotScheduler]执行线程：run()");
				schedule();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.currentThread().sleep(rate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}
	
	public void start(){
		new Thread(this).start();
	}
	

}

package cn.tomsnail.util.thread.timer;

import java.util.TimerTask;

public abstract class TimerEvent extends TimerTask{

	 private long delay = 0;
	 
	 private long period = 0;
	 
	 private boolean rund = false;
	 

	/**
	 * @return the delay
	 */
	public long getDelay() {
		return delay;
	}

	/**
	 * @param delay the delay to set
	 */
	public void setDelay(long delay) {
		this.delay = delay;
	}

	/**
	 * @return the period
	 */
	public long getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(long period) {
		this.period = period;
	}

	/**
	 * @return the rund
	 */
	public boolean isRund() {
		return rund;
	}

	/**
	 * @param rund the rund to set
	 */
	public void setRund(boolean rund) {
		this.rund = rund;
	}
	
	
}

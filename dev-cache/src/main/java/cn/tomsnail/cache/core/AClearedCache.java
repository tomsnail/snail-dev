package cn.tomsnail.cache.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *        清除缓存
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 下午2:03:34
 * @see 
 */
public abstract class AClearedCache implements ICache,ICleanCache,Runnable {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(AClearedCache.class);
	
	/**
	 * 清理时间
	 */
	protected long cleanTime = 1000l;
	
	/**
	 * 是否清理
	 */
	protected boolean isCleared = true;
	
	/**
	 * 是否运行
	 */
	protected boolean isRuning = true;

	public void run(){
		while(isRuning){
			try {
				Thread.currentThread().sleep(cleanTime<1?1000l:cleanTime);
				if(isCleared) clean();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public long getCleanTime() {
		return cleanTime;
	}

	public void setCleanTime(long cleanTime) {
		this.cleanTime = cleanTime;
	}

	public boolean isCleared() {
		return isCleared;
	}

	public void setCleared(boolean isCleared) {
		this.isCleared = isCleared;
	}

	public boolean isRuning() {
		return isRuning;
	}

	public void setRuning(boolean isRuning) {
		this.isRuning = isRuning;
	}
	
	
	
}

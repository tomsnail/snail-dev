package cn.tomsnail.snail.ext.cache.local;

/**
 *        本地缓存的对象
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月22日 下午2:24:25
 * @see 
 */
public class MapCacheObject<T> {

	private T t;
	
	private long expire;
	
	private long lastTime;
	
	public MapCacheObject(){
		
	}
	
	

	public MapCacheObject(T t, long expire, long lastTime) {
		this.t = t;
		this.expire = expire;
		this.lastTime = lastTime;
	}



	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	
	
	
}

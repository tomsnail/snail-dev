package cn.tomsnail.security.limit.bucket;

import java.util.concurrent.LinkedBlockingQueue;


/**
 *      令牌桶，用于限流  
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月15日 上午10:17:00
 * @see 
 */
public final class TokenBucketRequestLimit implements Runnable,IRequestLimit{

	/**
	 * 桶
	 */
	private  LinkedBlockingQueue<Long> buckets;
	
	/**
	 * 桶容量
	 */
	private  int bucket_capacity = 1000;
	
	/**
	 * 速率
	 */
	private final long rate = 50l; 
	
	/**
	 * 计数器
	 */
	private long count = 0;
	
	
	private boolean isRun = true;
	
	public TokenBucketRequestLimit(int bucket_capacity){
		this.bucket_capacity = bucket_capacity<1?1000:bucket_capacity;
		buckets = new  LinkedBlockingQueue<Long>(bucket_capacity);
		for(int i=0;i<bucket_capacity;i++){
			buckets.offer(count++);
		}
		new Thread(this).start();
	}
	
	public  boolean canPass(){
		Long token = buckets.poll();
		if(token!=null){
			return true;
		}
		return false;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(isRun){
			try {
				Thread.currentThread().sleep(rate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			buckets.offer(count++);
		}	
	}
	
	
	
	public int capacity(){
		return bucket_capacity;
	}
	
	public void tryStop(){
		isRun = false;
	}
	
	
}

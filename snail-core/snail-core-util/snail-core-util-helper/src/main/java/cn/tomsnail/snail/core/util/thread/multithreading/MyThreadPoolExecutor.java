package cn.tomsnail.snail.core.util.thread.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 自定义线程池
 * @author yangsong
 * @date 2012-5-18 上午9:47:04
 */
public class MyThreadPoolExecutor{
	
	private final byte[] syn = new byte[0];
	
	private ThreadPoolExecutor executor = null;

	private int corePoolSize = 1;
	
	private int maximumPoolSize = Integer.MAX_VALUE;
	
	private long keepAliveTime = 60;
	
	private TimeUnit unit = TimeUnit.SECONDS;
	
	private int queueCapacity = Integer.MAX_VALUE;
	
	private RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
	
	public MyThreadPoolExecutor(){
		
	}

	public void initThreadPoolExecutor(){
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, this.createQueue(queueCapacity), handler);
		this.executor = threadPoolExecutor;
	}
	
	/**
	 * @return the corePoolSize
	 */
	public int getCorePoolSize() {
		return corePoolSize;
	}

	/**
	 * @param corePoolSize the corePoolSize to set
	 */
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	/**
	 * @return the maximumPoolSize
	 */
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	/**
	 * @param maximumPoolSize the maximumPoolSize to set
	 */
	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	/**
	 * @return the keepAliveTime
	 */
	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	/**
	 * @param keepAliveTime the keepAliveTime to set
	 */
	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	/**
	 * @return the queueCapacity
	 */
	public int getQueueCapacity() {
		return queueCapacity;
	}

	/**
	 * @param queueCapacity the queueCapacity to set
	 */
	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

	/**
	 * @return the executor
	 */
	public ThreadPoolExecutor getExecutor() {
		return getThreadPoolExecutor();
	}

	/**
	 * @return the unit
	 */
	public TimeUnit getUnit() {
		return unit;
	}

	/**
	 * @return the handler
	 */
	public RejectedExecutionHandler getHandler() {
		return handler;
	}

	private ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
		synchronized (syn) {
			if(executor == null){
				this.initThreadPoolExecutor();
			}
			return this.executor;
		}
		
	}
	
	public void execute(Runnable command){
		ThreadPoolExecutor executor = getThreadPoolExecutor();
		executor.execute(command);
	}
	
	public void shutdown(){
		ThreadPoolExecutor executor = getThreadPoolExecutor();
		executor.shutdown();
	}
	
	public Future<?> submit(Runnable task) {
		ExecutorService executor = getThreadPoolExecutor();
		try {
			return executor.submit(task);
		}
		catch (RejectedExecutionException ex) {
			throw new RejectedExecutionException("Executor [" + executor + "] did not accept task: " + task, ex);
		}
	}
	
	public <T> Future<T> submit(Callable<T> task) {
		ExecutorService executor = getThreadPoolExecutor();
		try {
			return executor.submit(task);
		}
		catch (RejectedExecutionException ex) {
			throw new RejectedExecutionException("Executor [" + executor + "] did not accept task: " + task, ex);
		}
	}
	
	protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
		if (queueCapacity > 0) {
			return new LinkedBlockingQueue<Runnable>(queueCapacity);
		}
		else {
			return new SynchronousQueue<Runnable>();
		}
	}
	
	
}

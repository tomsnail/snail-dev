package cn.tomsnail.snail.core.log.ls;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.tomsnail.snail.core.util.string.StringUtils;



public class FileLogFactory {
	
	private final Map<String,LogSystem> LS_MAP = new ConcurrentHashMap<String, LogSystem>();
	
	private final Lock LOCK = new ReentrantLock();
	
	private static final FileLogFactory INSTANCE = new FileLogFactory();
	
	
	public static FileLogFactory instance() {
		return INSTANCE;
	}

	private FileLogFactory() {
		init();
	}
	
	private void init() {
		
	}
	
	public  LogSystem create(String fileName) {
		return create("logs", fileName);
	}
	
	public  LogSystem create(String path,String fileName) {
		return create(path, fileName, null);
	}
	
	public  LogSystem create(String path,String fileName,String format) {
		
		LOCK.lock();
		if(!LS_MAP.containsKey(fileName)) {
			LS_MAP.put(fileName, initLogSystem(path,fileName,format));
		}
		LOCK.unlock();
		
		return LS_MAP.get(fileName);
	}
	
	private LogSystem initLogSystem(String path,String fileName,String format) {
		
		if(StringUtils.isAnyBlank(path,fileName)) {
			return null;
		}
		return new Log4j2RollingFileSystem(path, fileName,format);
		
	}
	

}

package cn.tomsnail.log.ls;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import cn.tomsnail.util.string.StringUtils;

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
		
		LOCK.lock();
		if(!LS_MAP.containsKey(fileName)) {
			LS_MAP.put(fileName, initLogSystem(path,fileName));
		}
		LOCK.unlock();
		
		return LS_MAP.get(fileName);
	}
	
	private LogSystem initLogSystem(String path,String fileName) {
		
		if(StringUtils.isAnyBlank(path,fileName)) {
			return null;
		}
		return new Log4j2RollingFileSystem(path, fileName);
		
	}
	

}

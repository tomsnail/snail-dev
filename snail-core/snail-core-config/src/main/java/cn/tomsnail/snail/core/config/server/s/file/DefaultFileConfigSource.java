package cn.tomsnail.snail.core.config.server.s.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import cn.tomsnail.snail.core.config.server.s.AConfigSource;



/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月20日 下午1:50:56
 * @see 
 */
public class DefaultFileConfigSource extends AConfigSource implements FileAlterationListener {
	
	
	protected String path = "/";
	
	protected long interval = 5000;
	
	protected String suffix = ".properties";
	
	protected FileAlterationMonitor monitor = null;  
	
	public DefaultFileConfigSource(){
		
	}
	
	public DefaultFileConfigSource(String path,long interval){
		this.path = path;
		this.interval = interval;
		
	}
	
	@Override
	public void start(){
		 monitor = new FileAlterationMonitor(interval);
		 FileAlterationObserver  observer = new FileAlterationObserver(new File(path), new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getAbsolutePath().endsWith(suffix);
			}
		});
	     monitor.addObserver(observer);  
	     observer.addListener(this);
	     try {
			monitor.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public Map<String, String> getConfigData() {
		Map<String, String> configMap = new HashMap<String,String>();
		File file = new File(path);
		if(file.isDirectory()){
			File[] files = file.listFiles(new DefaultFilenameFilter(suffix));
			if(files!=null&&files.length>0){
				for(File f:files){
					Properties properties =  new Properties(); 
					InputStream in = null;
					Reader reader = null;
					try {
						in = new FileInputStream(f);
						reader = new InputStreamReader(in);
						properties.load(reader);
						Iterator<Object> it = properties.keySet().iterator();
						while(it.hasNext()){
							String key = it.next()+"";
							String value = properties.getProperty(key);
							configMap.put(key, parse(value));
						}
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						if(reader!=null){
							try {
								reader.close();
								reader = null;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if(in!=null){
							try {
								in.close();
								in = null;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						properties.clear();
						properties = null;
						f = null;
					}
				}
				return configMap;
			}
		}
		return null;
	}

	@Override
	public void onDirectoryChange(File arg0) {
		
	}

	@Override
	public void onDirectoryCreate(File arg0) {
		
	}

	@Override
	public void onDirectoryDelete(File arg0) {
		
	}

	@Override
	public void onFileChange(File arg0) {
		System.out.println("onFileChange");
		this.notifyObservers();
	}

	@Override
	public void onFileCreate(File arg0) {
		System.out.println("onFileCreate");
		this.notifyObservers();
	}

	@Override
	public void onFileDelete(File arg0) {
		System.out.println("onFileDelete");
		//this.notifyObservers();
	}

	@Override
	public void onStart(FileAlterationObserver arg0) {
		//System.out.println("onStart");
		//this.notifyObservers();
	}

	@Override
	public void onStop(FileAlterationObserver arg0) {
		
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	@Override
	public String parse(String value) {
		return value;
	}
	
	
	
}

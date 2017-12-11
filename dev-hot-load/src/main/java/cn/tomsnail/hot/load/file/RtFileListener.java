package cn.tomsnail.hot.load.file;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang.StringUtils;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月22日 上午9:47:25
	* @see 
	*/     
public class RtFileListener implements FileAlterationListener{
	
	private String path = "rt";
	
	private long interval = 2000;
	
	private RtFileCallBack fileCallBack;
	
	public static boolean isInited =false;
	
	public RtFileListener(String path,long interval,RtFileCallBack fileCallBack){
		this.path = path;
		this.interval = interval;
		this.fileCallBack = fileCallBack;
	}
	
	public void start() throws Exception{
		if(interval<0){
			interval = 2000l;
		}
		if(StringUtils.isBlank(path)){
			throw new FileNotFoundException("path is null");
		}
		if(fileCallBack==null){
			throw new NullPointerException("fileCallBack is null");
		}
	
		FileAlterationObserver observer = new FileAlterationObserver(new File(path), FileFilterUtils.or(FileFilterUtils.suffixFileFilter(".jar"),FileFilterUtils.nameFileFilter("service.properties")));
        observer.addListener(this);
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval,observer);
        monitor.start();
        
      
		
		
	}

	@Override
	public void onStart(FileAlterationObserver observer) {
		if(!isInited){
			  File[] fs = new File(path).listFiles();
				if(fileCallBack!=null){
					for(File f:fs){
						if(f.getName().endsWith("service.properties")){
							continue;
						}
						fileCallBack.call(f, FileChangeType.FChange);
					}
					
					fileCallBack.call(new File(this.path+File.separator+"service.properties"), FileChangeType.FChange);
				}
		}
		isInited = true;
	}

	@Override
	public void onDirectoryCreate(File directory) {
		if(fileCallBack!=null){
			fileCallBack.call(directory, FileChangeType.DCreate);
		}
	}

	@Override
	public void onDirectoryChange(File directory) {
		if(fileCallBack!=null){
			fileCallBack.call(directory, FileChangeType.DChange);
		}
	}

	@Override
	public void onDirectoryDelete(File directory) {
		if(fileCallBack!=null){
			fileCallBack.call(directory, FileChangeType.DDelete);
		}
	}

	@Override
	public void onFileCreate(File file) {
		if(fileCallBack!=null){
			fileCallBack.call(file, FileChangeType.FCreate);
		}
	}

	@Override
	public void onFileChange(File file) {
		if(fileCallBack!=null){
			System.out.println(file.getAbsolutePath());
			fileCallBack.call(file, FileChangeType.FChange);
		}
	}

	@Override
	public void onFileDelete(File file) {
		if(fileCallBack!=null){
			fileCallBack.call(file, FileChangeType.FDelete);
		}
	}

	@Override
	public void onStop(FileAlterationObserver observer) {
		
	}

}

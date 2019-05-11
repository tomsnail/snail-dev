package cn.tomsnail.snail.ext.pilot.hot.reload.file;

import java.io.File;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月22日 上午9:49:46
	* @see 
	*/     
    
public interface RtFileCallBack {

	public void call(File file,FileChangeType fileChangeType);
	
}

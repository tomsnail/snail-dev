package cn.tomsnail.config.server.s.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月20日 下午2:36:07
 * @see 
 */
public class DefaultFilenameFilter implements FilenameFilter{
	
	private String type = ".properties";
	
	public DefaultFilenameFilter(){
		
	}
	
	public DefaultFilenameFilter(String type){
		this.type = type;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(type);
	}

}

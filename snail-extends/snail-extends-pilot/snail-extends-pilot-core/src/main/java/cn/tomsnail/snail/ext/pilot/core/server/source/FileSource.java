package cn.tomsnail.snail.ext.pilot.core.server.source;



/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月7日 上午11:30:31
 * @see 
 */
public abstract class FileSource extends ASource{

	protected String file;

	
	public FileSource(String code) {
		super(code);
	}
	
	public abstract void initFile();

}

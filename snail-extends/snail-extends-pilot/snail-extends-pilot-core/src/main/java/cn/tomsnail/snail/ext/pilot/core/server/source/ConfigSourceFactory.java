package cn.tomsnail.snail.ext.pilot.core.server.source;

import java.util.HashMap;
import java.util.Map;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月7日 上午11:24:23
 * @see 
 */
public class ConfigSourceFactory {
	
	private static final ConfigSourceFactory _instance  = new ConfigSourceFactory();
	
	private Map<String,ISource> _sourceMap = new HashMap<String, ISource>();
	
	private ConfigSourceFactory(){
		_sourceMap.put("XML", new XmlSource());
	}
	
	public static ConfigSourceFactory instance(){
		return _instance;
	}

	public ISource getSource(String type){
		return _sourceMap.get(type);
	}
	
	public ISource getDefaultSource(){
		return getSource("XML");
	}
	
}

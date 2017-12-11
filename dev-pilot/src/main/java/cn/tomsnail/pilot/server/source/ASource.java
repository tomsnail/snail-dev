package cn.tomsnail.pilot.server.source;

import java.util.HashMap;
import java.util.Map;

import cn.tomsnail.pilot.model.ProxyInfo;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月7日 上午11:28:21
 * @see 
 */
public abstract class ASource implements ISource{

	protected String code;
	
	protected Map<String,ProxyInfo> map = new HashMap<String, ProxyInfo>();
	
	public ASource(String code){
		this.code = code;
		initMap();
	}
	
	public Map<String,ProxyInfo> getSourceData(){
		return map;
	}
	
	public abstract void initMap();
	
	
}

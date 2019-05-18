package cn.tomsnail.snail.ext.pilot.core.server.source;

import java.util.HashMap;
import java.util.Map;

import cn.tomsnail.snail.ext.pilot.core.model.ProxyInfo;


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
		setCode(code);
		initMap();
	}

	@Override
	public Map<String,ProxyInfo> getSourceData(){
		return map;
	}
	
	public abstract void initMap();


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

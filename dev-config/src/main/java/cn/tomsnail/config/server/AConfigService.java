package cn.tomsnail.config.server;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.tomsnail.config.server.s.AConfigSource;


/**
 *        配置服务抽象类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月20日 下午1:15:03
 * @see 
 */
public abstract class AConfigService implements IConfigObserver,IConfigService{	
	
	/**
	 * 配置源
	 */
	protected AConfigSource configSource;
	
	/**
	 * 配置映射
	 */
	protected Map<String,String> configMap = new ConcurrentHashMap<String,String>();
	
	public AConfigService(){
		
	}
	
	public AConfigService(AConfigSource configSource){
		this.configSource = configSource;
		init();
	}
	
	/**
	 *        初始化配置映射
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年6月20日 下午3:06:46
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public abstract void initConfigMap();
	
	
	@Override
	public synchronized void initConfigObject() {
		System.out.println("initConfigObject");
		Map<String,String> _configMap = this.getConfigSource().getConfigData();
		if(_configMap!=null){
			Iterator<String> it = _configMap.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				if(configMap.containsKey(key)){
					if(_configMap.get(key).equals(configMap.get(key))){
					}else{
						refresh(key,_configMap.get(key)+"");
						configMap.put(key, _configMap.get(key));
					}
				}else{
					configMap.put(key, _configMap.get(key));
					add(key,_configMap.get(key)+"");
				}
			}
		}
	}

	@Override
	public void refresh() {
		initConfigObject();
	}

	@Override
	public void init() {
		initConfigMap();
		if(configSource!=null){
			configSource.addObserver(this);
		}
		configSource.start();
	}

	public AConfigSource getConfigSource() {
		return configSource;
	}

	public void setConfigSource(AConfigSource configSource) {
		this.configSource = configSource;
	}
	
	
}

package cn.tomsnail.config.client;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *        配置客户端，链式结构
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月21日 上午11:15:56
 * @see 
 */
public abstract class AConfigCilent implements IConfigCilent{
	
	/**
	 * 是否初始化完成
	 */
	protected boolean inited = false;
	
	/**
	 * 配置客户端对象
	 */
	protected AConfigCilent configCilent;
	
	/**
	 * 配置类监听map
	 */
	protected Map<String,IConfigChangeListener> configChangeListeners = new ConcurrentHashMap<String, IConfigChangeListener>();

	protected Map<String,String> configMap = new ConcurrentHashMap<String,String>();
	
	public AConfigCilent(){
	}
	
	public AConfigCilent(AConfigCilent configCilent){
		this.configCilent = configCilent;
	}
	
	
	protected abstract String getConfig(String key);
	
	/**
	 *        获取配置，如果本地没有，则在configCilent中去获取
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:42:03
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception
	 */
	public String getConfig(String key,String defaultValue){
		String v = null;
		if(configMap.containsKey(key)){
			v =  configMap.get(key);
		}else{
			if(this.inited)
				v = getConfig(key);
			if(v!=null){
				configMap.put(key, v);
			}
			if(v==null&&configCilent!=null){
				v = configCilent.getConfig(key,defaultValue);
			}
		}
		if(v==null){
			v =  defaultValue;
		}
		return v;
	}
	
	protected boolean isEmpty(String value){
		if(value==null||value.equals("")||value.equals("null")){
			return true;
		}
		return false;
	}
	
	

	@Override
	public synchronized void addListener(Class changeListener){
		try {
			String className = changeListener.getName();
			if(!this.configChangeListeners.containsKey(className)){
				IConfigChangeListener o = (IConfigChangeListener) changeListener.newInstance();
				this.configChangeListeners.put(className, o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	/**
	 *        删除监听
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:41:13
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void removeListener(Class changeListener){
		this.configChangeListeners.remove(changeListener.getName());
	}
	
	
	/**
	 *        变化下发
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:41:42
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void changed(){
		Iterator<String> it = this.configChangeListeners.keySet().iterator();
		while(it.hasNext()){
			this.configChangeListeners.get(it.next()).changed();
		}
	}
	
	protected abstract boolean isDo();
	
}

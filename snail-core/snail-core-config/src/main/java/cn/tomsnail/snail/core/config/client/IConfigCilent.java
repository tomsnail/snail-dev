package cn.tomsnail.snail.core.config.client;

/**
 *        配置客户端
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月21日 下午12:15:26
 * @see 
 */
public interface IConfigCilent {

	
	/**
	 *        获取配置
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:39:50
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String getConfig(String key,String defaultValue);
	
	/**
	 *        添加监听
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:39:56
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void addListener(Class<? extends IConfigChangeListener> changeListener);	
}
